package com.danao.sr.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author : chenxiaolong5
 * @createDate : 11:52 2019/3/18
 * @message:
 */
@Component
@Data
public class HttpPoolProperties {

    private Integer maxTotal = 200;
    private Integer defaultMaxPerRoute = 100;
    private Integer connectTimeout = 5000;
    private Integer connectionRequestTimeout = 1000;
    private Integer socketTimeout = 65000;
    private Integer validateAfterInactivity = 2000;

}
