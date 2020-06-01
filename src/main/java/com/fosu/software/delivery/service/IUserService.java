package com.fosu.software.delivery.service;

import com.fosu.software.delivery.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.service
 * @Author: Boss_king
 * @CreateTime: 2020-06-01 16:10
 * @Description: User实体类业务层接口
 */
public interface IUserService {
    /*查询所有，用于测试*/
    public List<User> findAll();
    /*查询是否存在该用户，用于注册的验证用户是否输入了已存在的用户id*/
    public Object exitUser(String userId);
    /*注册用户，后续可能添加Spring security框架来进一步巩固数据安全性，敬请期待*/
    public Object userRegistration(User user);
}
