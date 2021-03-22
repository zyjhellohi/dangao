package com.danao.sr.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class WxMessageController {


    @RequestMapping("/wx/message/receive")
    public String receivedMessage(HttpServletRequest req){
        long startTime = System.currentTimeMillis();
        log.info("WxMessageController receivedMessage start=======> req={}",req);
        String resp = "你好，开发者";

        log.info("WxMessageController receivedMessage end=======> req={},resp={}, time={}",req,resp,System.currentTimeMillis()-startTime);
        return resp;
    }



}
