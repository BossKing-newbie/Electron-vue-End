package com.fosu.software.delivery.service;

import com.fosu.software.delivery.domain.UserInfo;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.service
 * @Author: Boss_king
 * @CreateTime: 2020-06-11 11:00
 * @Description: 用户个人信息的接口类
 */
public interface IUserInfoService {
    /*后端返回用户的个人信息*/
    public Object findUserInfo(String userId);
    /*更新个人信息*/
    public Object updateUserInfo(UserInfo userInfo);
}
