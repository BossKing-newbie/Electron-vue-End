package com.fosu.software.delivery.service.impl;

import com.fosu.software.delivery.dao.UserInfoMapper;
import com.fosu.software.delivery.domain.UserInfo;
import com.fosu.software.delivery.resultFormat.ResultUtils;
import com.fosu.software.delivery.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.service.impl
 * @Author: Boss_king
 * @CreateTime: 2020-06-11 11:01
 * @Description: 用户个人信息接口的实现类
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements IUserInfoService {
    private UserInfoMapper userInfoMapper;

    @Autowired
    public void setMapper(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public Object findUserInfo(String userId) {
        try{
            return ResultUtils.success(userInfoMapper.findUserInfo(userId));
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail(400,"get请求发送出错");
        }
    }

    @Override
    public Object updateUserInfo(UserInfo userInfo) {
        int row = 0;
        try{
            row = userInfoMapper.updateUserInfo(userInfo);
            if(row > 0) {
                return ResultUtils.success();
            }
        }catch (Exception e){
            return ResultUtils.fail(400,e.getLocalizedMessage());
        }
        return null;
    }
}
