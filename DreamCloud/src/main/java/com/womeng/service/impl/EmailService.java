package com.womeng.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.womeng.utils.RedisKeyUtils;
import com.womeng.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    private final RedisUtil redisUtil;

    private final CaptchaService captchaService;

    @Value("${spring.mail.username}")
    private String sender;

    /**
     * 发送注册验证码
     * @param email 目标邮箱：验证码接收方
     */
    public void getRegisterCaptcha(String email){
        sentEmailCaptcha(RedisKeyUtils.REGISTER,email,"【验证码】您的注册验证码为：");
    }

    /** 发送登录验证码
     * @param email 目标邮箱：验证码接收方
     */
    public void getLoginCaptcha(String email) {
        sentEmailCaptcha(RedisKeyUtils.LOGIN_EMAIL, email, "【验证码】您的登录验证码为：");
    }

    /**
     * 发送重置密码验证码
     * @param email 目标邮箱：验证码接收方
     */
    public void getResetCaptcha(String email) {
        sentEmailCaptcha(RedisKeyUtils.RESET, email, "【验证码】您正在尝试重置密码，验证码为：");
    }


    /**
     * @param type 验证码在redis的键名前缀，参考RedisKeyUtils类
     * @param email 正在尝试重置密码的用户邮箱
     * @param msg 邮件提醒信息
     */
    private void sentEmailCaptcha(String type, String email, String msg) {

        SimpleMailMessage message = new SimpleMailMessage();

        // 设置发件人
        message.setFrom("DreamCloud" + '<' + sender + '>');

        // 设置邮件主题
        message.setSubject("欢迎访问" + "DreamCloud");
        // 设置收件人
        message.setTo(email);
        //生成六位随机数
        String code = RandomUtil.randomNumbers(6);
        // 将验证码缓存到Redis
        redisUtil.set(RedisKeyUtils.initKey(type,email), code, 300);

        String content = msg + code + " 。 验证码五分钟内有效，逾期作废。\n\n\n" +
                "------------------------------\n\n\n";
        message.setText(content);

        log.info("发送邮箱验证码到:{}",email);
        // 发送邮件
        javaMailSender.send(message);
    }

}
