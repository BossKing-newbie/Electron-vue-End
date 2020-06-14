package com.fosu.software.delivery.dao;

import com.fosu.software.delivery.domain.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.dao
 * @Author: Boss_king
 * @CreateTime: 2020-06-02 11:04
 * @Description: dao层user_info数据库表的接口类
 */
@Repository
public interface UserInfoMapper {
    /*书写一个注册时的附带方法，注册成功后，我们需要为用户在user_info中初始化表*/
    public int userInfoInit(UserInfo userInfo);
    /*后端返回用户的个人信息*/
    public UserInfo findUserInfo(String userId);
    /*个人修改后的更新操作，更新成功会返回影响行数*/
    public int updateUserInfo(UserInfo userInfo);
}
