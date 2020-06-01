package com.fosu.software.delivery.service.impl;

import com.fosu.software.delivery.dao.UserMapper;
import com.fosu.software.delivery.domain.User;
import com.fosu.software.delivery.resultFormat.ResultUtils;
import com.fosu.software.delivery.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public Object exitUser(String userId) {
        if(!userMapper.exitUser(userId)){
            return ResultUtils.fail(110,"无此用户");
        }else{
          return ResultUtils.success();
        }
    }

    @Override
    public Object userRegistration(User user) {
        int code = 0;
        /*添加try和catch保证插入语句报错直接打印到控制台*/
        try {
            //插入时，需要判断对方是否存在于数据库
            if(!userMapper.exitUser(user.getUserId())){
                code=userMapper.userRegistration(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(code>0){
            return ResultUtils.success(user);
        }else{
            return ResultUtils.fail(111,"插入失败");
        }
    }
}
