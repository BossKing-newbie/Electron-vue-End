package com.fosu.software.delivery.dao;

import com.fosu.software.delivery.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.dao
 * @Author: Boss_king
 * @CreateTime: 2020-06-01 15:54
 * @Description: User实体类对应的Mybatis接口
 */
@Repository
public interface UserMapper {
    /*查询所有，用于测试*/
    public List<User> findAll();
    /*查询是否存在该用户，用于注册的验证用户是否输入了已存在的用户id*/
    public boolean exitUser(String userId);
    /*注册用户，后续可能添加Spring security框架来进一步巩固数据安全性，敬请期待*/
    /*注册成功会返回影响行数，用此来判断是否插入成功！*/
    public int userRegistration(User user);
    /*用户登录：后续会结合shiro进行数据安全性维护*/
    public User login(String userId);
    /*update用户绑定的手机号*/
    public int updateUserPhone(String userId,String userPhone);
    /*修改手机号需要返回userId,userPhone的接口*/
    public Map<String,String> checkUserPhone(String userId);
    /*修改密码*/
    public int updateUserPassword(String userId,String newPassword);
}
