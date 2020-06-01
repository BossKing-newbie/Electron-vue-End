package com.fosu.software.delivery.controller;

import com.fosu.software.delivery.domain.User;
import com.fosu.software.delivery.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @RequestMapping("/find")
    public List<User> findAll(){
        return userService.findAll();
    }
    @GetMapping(value = "/{userId}")
    public Object exitUser(@PathVariable("userId")String userId){
        return userService.exitUser(userId);
    }
    @PostMapping(value = "/register")
    public Object userRegistration(User user){
        System.out.println(user);
        return userService.userRegistration(user);
    }
}
