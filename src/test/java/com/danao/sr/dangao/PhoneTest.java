package com.danao.sr.dangao;

import com.alibaba.fastjson.JSONObject;
import com.danao.sr.vo.Caidan;
import com.danao.sr.vo.ShouCaidan;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

//
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class PhoneTest {


    @Test
    public  void validatePhone() {
        // 校验的号码
        String swissNumberStr = "044 668 18 00";
        String countCode = "CN";
        // 获取 PhoneNumberUtil 实例
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            // 解析号码
            Phonenumber.PhoneNumber swissNumberProto  = phoneUtil.parse(swissNumberStr, countCode);
            // 校验号码
            boolean isVaild = phoneUtil.isValidNumber(swissNumberProto); // return true
            System.out.println("号码校验结果：" + isVaild);
        } catch (NumberParseException e) {
            System.err.println("号码解析异常: " + e.toString());
        }
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        Caidan caidan = new Caidan();
        List<ShouCaidan> list = new ArrayList<>();
        ShouCaidan shouCaidan = new ShouCaidan();
        shouCaidan.setKey("rselfmenu_0_0");
        shouCaidan.setName("六寸");
        shouCaidan.setType("click");
        ShouCaidan shouCaidan1 = new ShouCaidan();
        shouCaidan1.setKey("rselfmenu_0_1");
        shouCaidan1.setName("八寸");
        shouCaidan1.setType("click");
        ShouCaidan shouCaidan2 = new ShouCaidan();
        shouCaidan2.setKey("rselfmenu_0_2");
        shouCaidan2.setName("十寸");
        shouCaidan2.setType("click");
        ShouCaidan shouCaidan3 = new ShouCaidan();
        shouCaidan3.setKey("rselfmenu_0_3");
        shouCaidan3.setName("特别定制");
        shouCaidan3.setType("click");
        list.add(shouCaidan);
        list.add(shouCaidan1);
        list.add(shouCaidan2);
        list.add(shouCaidan3);


        List<ShouCaidan> list1 = new ArrayList<>();
        ShouCaidan shouCaidan4 = new ShouCaidan();
        shouCaidan4.setKey("rselfmenu_0_4");
        shouCaidan4.setName("水果系列");
        shouCaidan4.setType("click");
        ShouCaidan shouCaidan5 = new ShouCaidan();
        shouCaidan5.setKey("rselfmenu_0_5");
        shouCaidan5.setName("网红系列");
        shouCaidan5.setType("click");
        ShouCaidan shouCaidan6 = new ShouCaidan();
        shouCaidan6.setKey("rselfmenu_0_6");
        shouCaidan6.setName("小点心系列");
        shouCaidan6.setType("click");
        list1.add(shouCaidan4);
        list1.add(shouCaidan5);
        list1.add(shouCaidan6);

        List<ShouCaidan> list2 = new ArrayList<>();
        ShouCaidan shouCaidan7 = new ShouCaidan();
        shouCaidan7.setKey("rselfmenu_0_7");
        shouCaidan7.setName("蛋糕小常识");
        shouCaidan7.setType("click");
        ShouCaidan shouCaidan8 = new ShouCaidan();
        shouCaidan8.setKey("rselfmenu_0_8");
        shouCaidan8.setName("面粉小常识");
        shouCaidan8.setType("click");
        ShouCaidan shouCaidan9 = new ShouCaidan();
        shouCaidan9.setKey("rselfmenu_0_9");
        shouCaidan9.setName("营养小常识");
        shouCaidan9.setType("click");
        ShouCaidan shouCaidan10 = new ShouCaidan();
        shouCaidan10.setKey("rselfmenu_0_10");
        shouCaidan10.setName("新闻小常识");
        shouCaidan10.setType("click");
        list2.add(shouCaidan7);
        list2.add(shouCaidan8);
        list2.add(shouCaidan9);
        list2.add(shouCaidan10);

        ShouCaidan shouCaidan11 = new ShouCaidan();
        ShouCaidan shouCaidan12 = new ShouCaidan();
        ShouCaidan shouCaidan13 = new ShouCaidan();

        shouCaidan11.setName("订购栏");
        shouCaidan12.setName("展示栏");
        shouCaidan13.setName("新闻娱乐");


        shouCaidan11.setSub_button(list);
        shouCaidan12.setSub_button(list1);
        shouCaidan13.setSub_button(list2);

        List<ShouCaidan> list3 = new ArrayList<>();
        list3.add(shouCaidan11);
        list3.add(shouCaidan12);
        list3.add(shouCaidan13);

        caidan.setButton(list3);
        String str = JSONObject.toJSONString(caidan);
        String encode = "UTF-8";
        if (str.equals(new String(str.getBytes(encode), encode))) {
            System.out.println(JSONObject.toJSONString(caidan));
        }

    }
}
