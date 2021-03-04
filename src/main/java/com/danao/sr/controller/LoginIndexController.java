package com.danao.sr.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

public class LoginIndexController {

    @RequestMapping("/")
    public String getString(HttpServletRequest request){

        return "index";
    }
}
