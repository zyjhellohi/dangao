package com.danao.sr.controller;

import com.alibaba.fastjson.JSONObject;
import com.danao.sr.service.WxCheckService;
import com.danao.sr.util.SignUtil;
import com.danao.sr.vo.Caidan;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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




    @PostMapping(value = "/tesss",produces = {"application/json; charset=UTF-8"})
    public void checkPostss(@RequestBody Caidan caidan) {
        try {
            log.info("qingqiu caidan={}",caidan);
        } catch (Exception e) {
            log.error("checkPost ====error",e);
        }

    }

}
