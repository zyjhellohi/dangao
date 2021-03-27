package com.danao.sr.vo;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class DangaoSubVO implements Serializable {
    /**
     * CREATE TABLE dangao_user_subscribe (
     * id bigint(20) NOT NULL AUTO_INCREMENT,
     * user_id varchar(64) DEFAULT NULL,
     * open_id varchar(64) NOT NULL COMMENT '开发者openid',
     * flag varchar(5) DEFAULT NULL COMMENT '状态',
     * create_time timestamp NULL DEFAULT NULL,
     * update_time timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     * PRIMARY KEY (id),
     * KEY INDEX_user_userId (user_id)
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订阅表';
     */
    private String id;
    private String userId;
    private String openId;
    private String flag;
    private Date createTime = new Date();
    private Date updateTime = new Date();

}

