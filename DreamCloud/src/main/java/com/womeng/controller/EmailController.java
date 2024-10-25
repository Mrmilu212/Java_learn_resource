package com.womeng.controller;

import com.womeng.entity.Result;
import com.womeng.entity.User;
import com.womeng.entity.dto.UserDTO;
import com.womeng.service.impl.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    // 注入emailService
    private final EmailService emailService;

    /**
     * 获取注册验证码
     * @param email 用户注册邮箱
     * @return 统一响应结果
     */
    @GetMapping("/registerCode")
    public Result getRegisterCode(@RequestParam String email){
        emailService.getRegisterCaptcha(email);
        return Result.success();
    }

    /**
     * 获取登录验证码
     * @param email 用户登录邮箱
     * @return 统一响应结果
     */
    @GetMapping("/loginCode")
    public Result getLoginCode(@RequestParam String email){
        emailService.getLoginCaptcha(email);
        return Result.success();
    }

    /**
     * 获取重置密码验证码
     * @param userDTO 用户登录信息
     * @return 统一响应结果
     */
    @GetMapping("/getResetCode")
    public Result getResetCode(@RequestBody UserDTO userDTO){
        emailService.getResetCaptcha(userDTO.getEmail());
        return Result.success();
    }

}