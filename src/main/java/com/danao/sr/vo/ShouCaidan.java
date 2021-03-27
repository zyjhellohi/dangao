package com.danao.sr.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ShouCaidan implements Serializable {
    private String type;
    private String name;
    private String key;
    private String url;
    private List<ShouCaidan> sub_button;
}
