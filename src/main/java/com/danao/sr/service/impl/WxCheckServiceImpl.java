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
        eventVO.setToUserName(params.get("ToUserName"));
        eventVO.setFromUserName(params.get("FromUserName"));
        eventVO.setCreateTime(new Date().getTime()+"");
        eventVO.setMsgType("text");
        eventVO.setContent("测试一波");
        String respXml = XmlUtil.getXml(eventVO);
        log.info("respXml={}",JSONObject.toJSONString(eventVO));
        resp.getWriter().write(JSONObject.toJSONString(eventVO));
//        if (StringUtils.isNotEmpty(respXml)) {
//            // 输出流
//            resp.getWriter().write(respXml);
//        }
    }


//
//    public static void main(String[] args) {
//        EventVO eventVO = new EventVO();
//        eventVO.setToUserName("ToUserName");
//        eventVO.setFromUserName("FromUserName");
//        eventVO.setCreateTime("CreateTime");
//        eventVO.setMsgType("MsgType");
//        eventVO.setEvent("Event");
//        eventVO.setEventKey("EventKey");
//        System.out.println(XmlUtil.getXml(eventVO));
//    }
}
