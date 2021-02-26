package com.danao.sr.controller;

import com.danao.sr.service.DangaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DangaoLoginController {

    @Autowired
    DangaoService dangaoService;

    @RequestMapping("/docker")
    public String index() {
        return "Hello Docker!";
    }

    @RequestMapping("/query")
    public void query(){
        System.out.println(dangaoService.query("123"));
    }
}
