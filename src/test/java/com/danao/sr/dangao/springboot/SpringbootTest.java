package com.danao.sr.dangao.springboot;


import com.danao.sr.service.DangaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTest {


    @Autowired
    DangaoService dangaoService;


    @Test
    public void bootTest(){
        System.out.println(dangaoService.query("12"));
    }

}
