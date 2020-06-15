package com.fosu.software.delivery.controller;

import com.fosu.software.delivery.domain.User;
import com.fosu.software.delivery.resultFormat.ResultUtils;
import com.fosu.software.delivery.service.impl.UserServiceImpl;
import com.fosu.software.delivery.smsVerification.SmsZhenziUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.controller
 * @Author: Boss_king
 * @CreateTime: 2020-06-01 16:24
 * @Description: User类的控制类
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {
    private UserServiceImpl userService;
    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
    /*测试案例使用*/
  /*  @RequestMapping("/find")
    public List<User> findAll(){
        return userService.findAll();
    }*/
    @GetMapping(value = "/{userId}")
    public Object exitUser(@PathVariable("userId")String userId){
        return userService.exitUser(userId);
    }
    @PostMapping(value = "/register")
    public Object userRegistration(User user){
        //System.out.println(user);
        return userService.userRegistration(user);
    }
    @PostMapping(value = "/login")
    public Object userLogin(@RequestParam(value="account") String userId,
                            @RequestParam(value = "pass") String userPassword){
        return userService.login(userId,userPassword);
    }
    /*发送短信验证码*/
    @PostMapping(value = "/send_sms")
    public Object sendSMS(@RequestParam("newPhone")String newPhone, HttpServletRequest request){
        String result = SmsZhenziUtils.sendSms(newPhone,request);
        JsonObject returnData = JsonParser.parseString(result).getAsJsonObject();
        if(returnData.get("code").toString().equals("0")){
            return ResultUtils.success(returnData.toString());
        }else{
            return ResultUtils.fail(400,"手机验证码发送失败",returnData);
        }
    }
    /*接收短信验证码接口，即用户提交验证码,
      传入参数：userId:用户账号
    * newPhone:更改绑定手机号
    * randomCode:手机短信验证码*/
    @PostMapping(value = "/receive_sms")
    public Object receiveSMS(@RequestParam("userId")String userId,
                             @RequestParam("newPhone")String newPhone,
                             @RequestParam("randomCode")String randomCode,
                             HttpServletRequest request){
        //获取map
        Map<String,String> map=SmsZhenziUtils.receiveSMS(randomCode,request);
        /*验证成功*/
        if(map.get("code").equals("200")){
            if(userService.updateUserPhone(userId,newPhone)){
                return ResultUtils.success(map);
            }else{
                return ResultUtils.fail(100,"更新失败");
            }
        }else {
            return ResultUtils.fail(400,"验证失败");
        }
    }
    /*查询用户所在手机号*/
    @GetMapping("/check_phone/{userId}")
    public Object checkUserPhone(@PathVariable("userId")String userId){
        return userService.checkUserPhone(userId);
    }
    /*修改密码接口*/
    @PostMapping("/update_pwd")
    public Object updatePassword(@RequestParam("userId")String userId,
                                 @RequestParam("oldPassword")String oldPassword,
                                 @RequestParam("newPassword")String newPassword){
        return userService.updatePassword(userId,oldPassword,newPassword);
    }
}
