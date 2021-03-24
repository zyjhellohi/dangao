package com.danao.sr.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.danao.sr.service.WxCheckService;
import com.danao.sr.util.SignUtil;
import com.danao.sr.util.XmlUtil;
import com.danao.sr.vo.EventVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;


@Slf4j
@Service
public class WxCheckServiceImpl implements WxCheckService {


    /**
     * 处理微信授权
     * @param req
     * @param resp
     */
    @Override
    public void checkJurisdiction(HttpServletRequest req, HttpServletResponse resp) {


        String signature = req.getParameter("signature");
        // 时间戳
        String timestamp = req.getParameter("timestamp");
        // 随机数
        String nonce = req.getParameter("nonce");
        // 随机字符串
        String echostr = req.getParameter("echostr");

        log.info("signature={},timestamp={},nonce={},echostr={}",signature,timestamp,nonce,echostr);
        try {
            if (SignUtil.checkSignature(signature, timestamp, nonce))resp.getWriter().write(echostr);
            log.info("微信校验成功=====返回echostr={}",echostr);
        }catch (Exception e){
            log.error("返回失败====",e);
        }
    }

    /**
     * 处理微信消息
     * @param req
     * @param resp
     *
     *  private String ToUserName;
     *     private String FromUserName;
     *     private String CreateTime;
     *     private String MsgType;
     *     private String Event;
     *     private String EventKey;
     */
    @Override
    public void processingMessages(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        log.info("processingMessages start");
        InputStream inputStream = req.getInputStream();
        Map<String, String> params = XmlUtil.xmlToMap(inputStream);
        log.info("processingMessages map={}", JSONObject.toJSONString(params));


        EventVO eventVO = new EventVO();
        eventVO.setToUserName("<![CDATA["+params.get("FromUserName")+"]]>");
        eventVO.setFromUserName("<![CDATA["+params.get("ToUserName")+"]]>");
        eventVO.setCreateTime("<![CDATA["+new Date().getTime()+"]]>");
        eventVO.setMsgType("<![CDATA[text]]>");
        eventVO.setContent("<![CDATA[测试一波]]>");
        String respXml = XmlUtil.getXml(eventVO);
        resp.setCharacterEncoding("UTF-8");
        log.info("respXml={}",respXml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>","").replaceAll("&lt;","<").replaceAll("&gt;",">").trim());
        resp.getWriter().print(respXml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>","").replaceAll("&lt;","<").replaceAll("&gt;",">").trim());
//        if (StringUtils.isNotEmpty(respXml)) {
//            // 输出流
//            resp.getWriter().write(respXml);
//        }
    }



    public static void main(String[] args) {
        EventVO eventVO = new EventVO();
        eventVO.setToUserName("<![CDATA[FromUserName]]>");
        eventVO.setFromUserName("<![CDATA[ToUserName]]>");
        eventVO.setCreateTime("<![CDATA["+new Date().getTime()+"]]>");
        eventVO.setMsgType("<![CDATA[text]]>");
        eventVO.setContent("<![CDATA[测试一波]]>");

        System.out.println(XmlUtil.getXml(eventVO).replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>","").replaceAll("&lt;","<").replaceAll("&gt;",">").trim());
    }
}
