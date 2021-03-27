package com.danao.sr.vo.wx;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@Data
@ToString
public class SendKFVO  implements Serializable {

    private String touser;
    private String msgtype;
    private Map text;
    private Map customservice;

}
