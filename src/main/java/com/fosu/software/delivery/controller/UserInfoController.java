package com.fosu.software.delivery.controller;

import com.fosu.software.delivery.domain.UserInfo;
import com.fosu.software.delivery.qiniuyun.QiNiuYunUpload;
import com.fosu.software.delivery.resultFormat.ResultUtils;
import com.fosu.software.delivery.service.impl.UserInfoServiceImpl;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.controller
 * @Author: Boss_king
 * @CreateTime: 2020-06-11 11:04
 * @Description: 用户个人信息的后端控制层
 */
@RestController
@RequestMapping("/userInfo")
@CrossOrigin(origins = "*")
public class UserInfoController {
    private UserInfoServiceImpl userInfoService;

    @Autowired
    public void setUserInfoService(UserInfoServiceImpl userInfoService) {
        this.userInfoService = userInfoService;
    }

    /*编辑接口区*/

    /*查询个人信息接口*/
    @GetMapping(value = "/{userId}")
    public Object findUserInfo(@PathVariable("userId") String userId){
        return userInfoService.findUserInfo(userId);
    }
    /*返回上传到七牛云的图片地址*/
    @PostMapping(value = "/upload")
    public Object userAvatarUpload(
            @RequestParam("avatar")MultipartFile file,@RequestParam("avatarName") String avatarName) throws Exception {
        Map<String,String> map=new HashMap<>();
        String randomAlphanumeric= RandomStringUtils.randomAlphanumeric(5)+"-"+avatarName+"-"+file.getOriginalFilename();
        map.put("imageUrl",QiNiuYunUpload.updateFile(file,randomAlphanumeric));
        return ResultUtils.success(map);
    }
    /*用户信息更新接口*/
    @PostMapping(value = "/update")
    public Object updateUserInfo(UserInfo userInfo){
        return userInfoService.updateUserInfo(userInfo);
    }
}
