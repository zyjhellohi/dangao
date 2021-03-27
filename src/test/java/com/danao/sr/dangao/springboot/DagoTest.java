package com.danao.sr.dangao.springboot;

import com.alibaba.fastjson.JSONObject;
import com.danao.sr.service.DangaoService;
import com.danao.sr.service.SubLogService;
import com.danao.sr.service.SubService;
import com.danao.sr.vo.DangaoSubLogVO;
import com.danao.sr.vo.DangaoSubVO;
import com.danao.sr.vo.DangaoUserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DagoTest {

    @Autowired
    DangaoService dangaoService;

    @Autowired
    SubService  subService;

    @Autowired
    SubLogService subLogService;

    @Test
    public void insert(){
        DangaoUserVO dangaoUserVO = new DangaoUserVO();
        dangaoUserVO.setMobile("15927298757");
        dangaoUserVO.setAddress("湖北黄冈");
        dangaoUserVO.setFlag("Y");
        dangaoUserVO.setPassword("123456");
        dangaoUserVO.setUserId("1234567");
        dangaoUserVO.setCreateTime(new Date());
        dangaoUserVO.setName("zyj");
        dangaoUserVO.setUpdateTime(new Date());
        dangaoService.insert(dangaoUserVO);
    }

    @Test
    public void insertSub(){
        DangaoSubVO dangaoSubVO = new DangaoSubVO();
        dangaoSubVO.setCreateTime(new Date());
        dangaoSubVO.setFlag("N");
        dangaoSubVO.setOpenId("123456");
        dangaoSubVO.setUserId("1241414");
        dangaoSubVO.setUpdateTime(new Date());
        subService.insert(dangaoSubVO);
    }
    @Test
    public void querySub(){
        DangaoSubVO dangaoSubVO = new DangaoSubVO();
        dangaoSubVO.setOpenId("123456");
        System.out.println(JSONObject.toJSONString(subService.query(dangaoSubVO)));
    }

    @Test
    public void updateSub(){
        DangaoSubVO dangaoSubVO = new DangaoSubVO();
        dangaoSubVO.setOpenId("123456");
        dangaoSubVO.setFlag("N");
        subService.update(dangaoSubVO);
    }

    @Test
    public void insertSubLog(){
        DangaoSubLogVO dangaoSubLogVO = new DangaoSubLogVO();
        dangaoSubLogVO.setCreateTime(new Date());
        dangaoSubLogVO.setName("郑");
        dangaoSubLogVO.setOpenId("123456");
        dangaoSubLogVO.setUserId("1241414");
        dangaoSubLogVO.setOperate("眼");
        dangaoSubLogVO.setUpdateTime(new Date());
        subLogService.insert(dangaoSubLogVO);
    }


}
