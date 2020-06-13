package com.fosu.software.delivery.domain;

import java.io.Serializable;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.domain
 * @Author: Boss_king
 * @CreateTime: 2020-06-01 21:01
 * @Description: 数据库表user_info的实体类
 */
public class UserInfo implements Serializable {
    /*
    * 用户账号：userId
    * 用户姓名:userName
    * 用户性别：userSex
    * 用户地址：userAddress
    * 用户积分：userIntegral
    * 用户头像：userAvatar
    * 用户地址省市编码：userProvince*/
    private String userId;
    private String userName;
    private String userSex;
    private String userAddress;
    private String userAvatar;
    private String userProvince;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }
}
