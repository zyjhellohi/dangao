package com.danao.sr.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping("/login")
    public void login(){
        System.out.println("ceshi");
    }
}
