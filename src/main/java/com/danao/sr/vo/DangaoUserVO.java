package com.danao.sr.vo;


import java.io.Serializable;
import java.util.Date;

public class DangaoUserVO implements Serializable {
    /**
     * #用户信息表
     *
     * CREATE TABLE dangao_user (
     * id bigint(20) NOT NULL AUTO_INCREMENT,
     * user_id varchar(64) DEFAULT NULL,
     * name varchar(40) DEFAULT NULL,
     * password varchar(25) DEFAULT NULL COMMENT '密码',
     * address varchar(64) DEFAULT NULL COMMENT '默认收货地址',
     * mobile varchar(20) DEFAULT NULL COMMENT '手机号码',
     * flag varchar(5) DEFAULT NULL COMMENT '状态',
     * create_time timestamp NULL DEFAULT NULL,
     * update_time timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     * PRIMARY KEY (id),
     * KEY INDEX_user_userId (user_id)
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
     */
    private String id;
    private String userId;
    private String name;
    private String password;
    private String address;
    private String mobile;
    private String flag;
    private Date createTime;
    private Date updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public String toString() {
        return "DangaoUserVO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", flag='" + flag + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

