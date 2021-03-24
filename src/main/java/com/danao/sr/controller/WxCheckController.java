package com.danao.sr.controller;

import com.alibaba.fastjson.JSONObject;
import com.danao.sr.service.WxCheckService;
import com.danao.sr.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Slf4j
@RestController
public class WxCheckController {


    @Autowired
    private WxCheckService wxCheckService;


    @GetMapping("/wx/check")
    public void checkGet(HttpServletRequest request, HttpServletResponse response) {
        wxCheckService.checkJurisdiction(request, response);
    }

    @PostMapping(value = "/wx/check",produces = {"application/xml; charset=UTF-8"})
    public void checkPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            wxCheckService.processingMessages(request, response);
        } catch (Exception e) {
            log.error("checkPost ====error",e);
        }

    }

}
