package com.danao.sr.dangao;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
