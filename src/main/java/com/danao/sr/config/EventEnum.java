package com.danao.sr.config;

public enum EventEnum {

    SUBSCRIBE("subscribe", "订阅"),
    UNSUBSCRIBE("unsubscribe", "取消订阅");

    private String type;

    private String msg;

    EventEnum(String type, String msg) {
        this.type = type;
        this.msg = msg;
    }


    public  String getType() {
        return this.type;
    }

    public String getMsg() {
        return this.msg;
    }

    public static EventEnum getByType(String type){
        for(EventEnum eventEnum : values()){
            if (eventEnum.getType().equals(type)) {
                //获取指定的枚举
                return eventEnum;
            }
        }
        return null;
    }
}
