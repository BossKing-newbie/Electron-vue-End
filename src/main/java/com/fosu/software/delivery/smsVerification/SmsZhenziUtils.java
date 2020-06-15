package com.fosu.software.delivery.smsVerification;

import com.zhenzi.sms.ZhenziSmsClient;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.smsVerification
 * @Author: Boss_king
 * @CreateTime: 2020-06-14 22:16
 * @Description: 榛子云发送短信验证码的工具类
 */
public class SmsZhenziUtils {
    // 回调地址
    private static String API_URL=SmsZhenzi.apiUrl;
    private static String APP_ID=SmsZhenzi.appId;
    private static String APP_SECRET=SmsZhenzi.appSecret;
    private static ZhenziSmsClient client = new ZhenziSmsClient(API_URL, APP_ID, APP_SECRET);
    private static String SMS_TEMPLATE_ID=SmsZhenzi.templateId;

    /*引入密码加密,用于验证码加密*/
    private static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static String sendSms(String userPhone, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        /*获取session*/
        HttpSession session = request.getSession();
        String result=""; //接收结果json数据
        params.put("number", userPhone);
        params.put("templateId", SMS_TEMPLATE_ID);
        String[] templateParams = new String[2];
        templateParams[0] = RandomStringUtils.randomNumeric(4);
        session.setAttribute("randomCode",passwordEncoder().encode(templateParams[0]));
        templateParams[1] = "1分钟";
        params.put("templateParams", templateParams);
        try{
            result = client.send(params);
            Timer timer = new Timer();
            /*设置定时任务，1分钟后删除session中的值*/
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    session.removeAttribute("randomCode");
                    timer.cancel();
                }
            }, 60 * 1000);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        System.out.println(result);
        return result;
    }
    public static Map<String,String> receiveSMS(String randomCode,HttpServletRequest request){
        /*获取session*/
        HttpSession session = request.getSession();
        Map<String,String> map=new HashMap<>();
        /*将发送的验证码与session中的加盐md5码解码后进行匹配*/
        if(session.getAttribute("randomCode") == null){
            map.put("code","400");
            map.put("msg","验证超时");
        }else{
            if(passwordEncoder().matches(randomCode,session.getAttribute("randomCode").toString())){
                map.put("code","200");
                map.put("msg","验证成功");
                session.removeAttribute("randomCode");
            }else{
                map.put("code","400");
                map.put("msg","验证失败");
            }
        }
        return map;
    }
}
