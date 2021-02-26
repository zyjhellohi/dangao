package com.danao.sr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.danao.sr.mapper")
public class DangaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DangaoApplication.class, args);
    }

}
