package com.danao.sr.vo;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
@Data
public class DangaoSubLogVO implements Serializable {
    /**
     CREATE TABLE dangao_user_subscribe_log (
     id bigint(20) NOT NULL AUTO_INCREMENT,
     user_id varchar(64) DEFAULT NULL,
     name varchar(64) DEFAULT NULL,
     open_id varchar(64) NOT NULL COMMENT '开发者openid',
     operate varchar(5) DEFAULT NULL COMMENT '状态',
     create_time timestamp NULL DEFAULT NULL,
     update_time timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     PRIMARY KEY (id),
     KEY INDEX_user_userId (user_id)
     ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订阅表历史表';

     */
    private String id;
    private String userId;
    private String name;
    private String openId;
    private String operate;
    private Date createTime;
    private Date updateTime;



}

