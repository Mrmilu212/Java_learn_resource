package com.womeng.utils;

import org.springframework.stereotype.Component;

/**
 * 用于统一Redis中key的格式
 */
@Component
public class RedisKeyUtils {

    // 注册 邮箱验证码
    public static final String REGISTER = "register:";
    // 登录 邮箱验证码
    public static final String LOGIN_EMAIL = "captcha:login:email:";
    // 重置密码 邮箱验证码
    public static final String RESET = "captcha:reset:email:";
    // 缓存用户数据
    public static final String USER = "user:";
    // 通用验证码
    public static final String CAPTCHA = "captcha:common:";
    // 存储JWT
    public static final String JWT = "jwt:user:";
    // 临时JWT
    public static final String TEMP_JWT = "jwt:temp:user:";

    /**
     * @param type 类型&键名的前缀
     * @param id 键的唯一标识
     * @return  拼接好的key
     */
    public static String initKey(String type,String id){
        return type + id;
    }

    /**
     * 初始化临时token的键
     * @param type 类型&键名的前缀
     * @param email 用户邮箱
     * @param captcha 邮箱验证码
     * @return 拼接好的key
     */
    public static String initTempKey(String email , String captcha){
        String id = email + ":" + captcha;
        return initKey(TEMP_JWT,id);
    }



}
