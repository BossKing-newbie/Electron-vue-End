package com.fosu.software.delivery.service.impl;

import com.fosu.software.delivery.dao.UserInfoMapper;
import com.fosu.software.delivery.dao.UserMapper;
import com.fosu.software.delivery.domain.User;
import com.fosu.software.delivery.domain.UserInfo;
import com.fosu.software.delivery.resultFormat.ResultUtils;
import com.fosu.software.delivery.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.service.impl
 * @Author: Boss_king
 * @CreateTime: 2020-06-01 16:11
 * @Description: UserService业务层接口的实现类
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    /*对SpringIOC容器注入Dao层对象*/
    private UserMapper userMapper;
    private UserInfoMapper userInfoMapper;
    @Autowired
    public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /*引入密码加密*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public Object exitUser(String userId) {
        if(!userMapper.exitUser(userId)){
            return ResultUtils.success();
        }else{
            return ResultUtils.fail(404,"用户已存在");
        }
    }
    /*user注册方法*/
    @Override
    public Object userRegistration(User user) {
        int code = 0;
        int userInfoCode=0; //验证是否初始化插入user_info表
        /*创建一个对象实现初始化表*/
        UserInfo userInfo=new UserInfo();
        userInfo.setUserId(user.getUserId());
        userInfo.setUserSex(null);
        userInfo.setUserName(null);
        userInfo.setUserAddress(null);
        userInfo.setUserAvatar(null);
        userInfo.setUserProvince(null);
        /*添加try和catch保证插入语句报错直接打印到控制台*/
        try {
            //插入时，需要判断对方是否存在于数据库
            if(!userMapper.exitUser(user.getUserId())){
                user.setUserPassword(passwordEncoder().encode(user.getUserPassword()));
                code=userMapper.userRegistration(user);
                userInfoCode=userInfoMapper.userInfoInit(userInfo);
            }
        }catch (Exception e){
            return ResultUtils.fail(400,e.toString());
        }
        if(code>0&&userInfoCode>0){
            return ResultUtils.success(userInfo);
        }
        return null;
    }

    @Override
    public Object login(String userId,String userPassword) {
        if(!userMapper.exitUser(userId)){
            return ResultUtils.fail(404,"账户不存在");
        }else{
            try{
                User user=userMapper.login(userId);
                if(passwordEncoder().matches(userPassword,user.getUserPassword())){
                    return ResultUtils.success(userInfoMapper.findUserInfo(userId));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return ResultUtils.fail(400,"账户密码错误！");
        }
    }
}
