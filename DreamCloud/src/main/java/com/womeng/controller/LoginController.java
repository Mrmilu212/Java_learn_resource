package com.womeng.controller;


import com.womeng.entity.Result;
import com.womeng.entity.dto.UserDTO;
import com.womeng.entity.info.EmailVerifyInfo;
import com.womeng.entity.info.LoginInfo;
import com.womeng.entity.info.ResetPwdInfo;
import com.womeng.entity.info.SignUpInfo;
import com.womeng.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * userInfo 前端控制器
 * </p>
 *
 * @author womeng
 * @since 2024-09-10
 */
@Slf4j
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    // 注入UserService
    private final UserServiceImpl userService;

    /**
     * 默认登录方式：通过用户名登录和密码
     * @param info 用户的登录信息
     * @return 统一响应结果
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginInfo info){
        return userService.login(info);
    }

    /**
     * 重置密码前，检查账户的信息并发送重置验证码
     * @param info  用户信息:包含email或用户名
     * @return 统一响应结果
     */
    @PostMapping("/preReset")
    public Result preReset(@RequestBody UserDTO info){
        return userService.preReset(info);
    }

    /**
     * 用户注册接口
     * @param userInfo // 用户的注册信息，包括验证码id、验证码文本和用户注册信息
     * @return 统一响应结果
     */
    @PostMapping("/signup")
    public Result signUp(@RequestBody SignUpInfo userInfo){
        return userService.signUp(userInfo);
    }

    /**
     * 检查重置密码的验证码
     * @param info 用户提交的验证信息
     * @return 统一响应结果
     */
    @PostMapping("/verifyResetCaptcha")
    public Result verifyResetCaptcha(@RequestBody EmailVerifyInfo info){
        return userService.verifyResetCaptcha(info);
    }

    /**
     * 重置密码接口
     * @param info 包含要重置密码账户的email和newPwd属性
     * @return 统一响应结果
     */
    @PutMapping("/resetPwd")
    public Result resetPwd(@RequestBody ResetPwdInfo info){
        return userService.resetPwd(info);
    }
}
