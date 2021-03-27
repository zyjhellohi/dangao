package com.danao.sr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan("com.danao.sr.mapper")
public class DangaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DangaoApplication.class, args);
    }



}
