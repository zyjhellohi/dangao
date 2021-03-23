package com.danao.sr.controller;

import com.alibaba.fastjson.JSONObject;
import com.danao.sr.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
@RestController
public class WxCheckController {

    @RequestMapping("/wx/check")
    public String check(HttpServletRequest request){

        log.info("c测试测试");
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        Enumeration params = request.getParameterNames();
        while(params.hasMoreElements()){
            String paramName = (String)params.nextElement();
            log.info( "Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
        }



        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            log.info("校验微信token成功");
        }

        return echostr;
    }

}
