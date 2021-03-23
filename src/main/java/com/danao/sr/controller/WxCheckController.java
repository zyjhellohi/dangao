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
    public void check(HttpServletRequest request){



    }

}
