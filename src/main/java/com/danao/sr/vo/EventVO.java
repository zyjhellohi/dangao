package com.danao.sr.vo;


import lombok.Data;

@Data
public class EventVO {

    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
    private String Event;
    private String EventKey;




}
