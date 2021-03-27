package com.danao.sr.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.danao.sr.config.EventEnum;
import com.danao.sr.config.RedisUtil;
import com.danao.sr.mapper.SubLogMapper;
import com.danao.sr.mapper.SubMapper;
import com.danao.sr.service.SubLogService;
import com.danao.sr.service.SubService;
import com.danao.sr.service.WxCheckService;
import com.danao.sr.util.HttpClientUtils;
import com.danao.sr.util.SignUtil;
import com.danao.sr.util.UuidUtil;
import com.danao.sr.util.XmlUtil;
import com.danao.sr.vo.DangaoSubVO;
import com.danao.sr.vo.EventVO;
import com.danao.sr.vo.wx.SendKFVO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Slf4j
@Service
public class WxCheckServiceImpl implements WxCheckService {

    @Autowired
    private HttpClientUtils httpClientUtils;

    @Value("${tian.qi.url}")
    private String tianQiUrl;

    @Value("${wx.get.token.url}")
    private String wxTokenUrl;

    @Value("${wx.send.kf.url}")
    private String wxKfnUrl;

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private SubLogMapper subLogMapper;

    @Autowired
    private SubMapper subMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 处理微信授权
     *
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

        log.info("signature={},timestamp={},nonce={},echostr={}", signature, timestamp, nonce, echostr);
        try {
            if (SignUtil.checkSignature(signature, timestamp, nonce)) resp.getWriter().write(echostr);
            log.info("微信校验成功=====返回echostr={}", echostr);
        } catch (Exception e) {
            log.error("返回失败====", e);
        }
    }

    /**
     * 处理微信消息
     *
     * @param req
     * @param resp private String ToUserName;
     *             private String FromUserName;
     *             private String CreateTime;
     *             private String MsgType;
     *             private String Event;
     *             private String EventKey;
     */


    @Override
    public void processingMessages(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        log.info("processingMessages start");
        InputStream inputStream = req.getInputStream();
        Map<String, String> params = XmlUtil.xmlToMap(inputStream);
        log.info("processingMessages map={}", JSONObject.toJSONString(params));
        if (StringUtils.isEmpty(params.get("Event"))) return;
        resp.setCharacterEncoding("UTF-8");

        String event = params.get("Event");

        switch (EventEnum.getByType(event)) {
            case SUBSCRIBE:
                subscribeMethod(params,resp);
            case UNSUBSCRIBE:
                System.out.println("1");
            default:
                System.out.println("default");
        }


//        EventVO eventVO = new EventVO();
//        eventVO.setToUserName("<![CDATA[" + params.get("FromUserName") + "]]>");
//        eventVO.setFromUserName("<![CDATA[" + params.get("ToUserName") + "]]>");
//        eventVO.setCreateTime("<![CDATA[" + new Date().getTime() + "]]>");
//        eventVO.setMsgType("<![CDATA[text]]>");
//        eventVO.setContent("<![CDATA[" + result("蕲春") + "]]>");
//        String respXml = XmlUtil.getXml(eventVO);
//
//        log.info("respXml={}", respXml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "").replaceAll("&lt;", "<").replaceAll("&gt;", ">").trim());
//        resp.getWriter().print(respXml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "").replaceAll("&lt;", "<").replaceAll("&gt;", ">").trim());
//        if (StringUtils.isNotEmpty(respXml)) {
//            // 输出流
//            resp.getWriter().write(respXml);
//        }
    }


    //---------------------------------------------
    //关注方法
    public void subscribeMethod(Map<String, String> params, HttpServletResponse resp) throws Exception{

        //保存关注者信息
        DangaoSubVO dangaoSubVO = new DangaoSubVO();
        dangaoSubVO.setFlag("Y");
        dangaoSubVO.setOpenId(params.get("FromUserName"));
        dangaoSubVO.setUserId(UuidUtil.getUserId());
        subMapper.insert(dangaoSubVO);

        //调用客服信息返回用户关注问候
        String token = getWxToken();
        if (StringUtils.isEmpty(token)) return;
        Map map = new HashMap();
        map.put("content", "谢谢你关注竹林湖最牛掰的公众号");
        Map map1 = new HashMap();
        map1.put("kf_account", "Jm001@Zlhsghp");
        SendKFVO sendKFVO = new SendKFVO();
        sendKFVO.setMsgtype("text");
        sendKFVO.setText(map);
        sendKFVO.setCustomservice(map1);
        log.info("发送客服消息给关注用户，微信消息体为={}", JSONObject.toJSONString(sendKFVO));
        httpCommentPost(JSONObject.toJSONString(sendKFVO),wxKfnUrl+token);
        return;
    }


    //------------调用微信接口
    public String getWxToken() {
        String token = null;
        JSONObject access_token = null;
        try {
            token = (String) redisUtil.get("token");
            log.info("从redis中获取的token={}", token);
            if (StringUtils.isNotEmpty(token)) return token;
            log.info("开始向微信服务器获取token");
            HttpGet httpGet = new HttpGet(wxTokenUrl);
            HttpResponse execute = httpClient.execute(httpGet);

            access_token = JSONObject.parseObject(httpClientUtils.getHttpResult(execute));
            token = access_token.getString("access_token");
            if (StringUtils.isNotEmpty(token)) redisUtil.set("token", token, 7200);
        } catch (Exception e) {
            log.error("通过http获取token失败 resp = {}, error", access_token.toString(), e);
            return token;
        }
        return token;
    }


    private void httpCommentPost(String obj, String url) throws IOException {
        HttpPost httpPost = httpClientUtils.getHttpPost(obj, url);
        HttpResponse execute = httpClient.execute(httpPost);
        httpClientUtils.getHttpResult(execute);
    }


}