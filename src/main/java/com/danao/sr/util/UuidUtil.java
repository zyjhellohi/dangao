package com.danao.sr.util;

import java.util.UUID;

public class UuidUtil {


    public static String getUserId(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(getUserId());
    }
}
