package com.womeng.controller;

import com.womeng.entity.Result;
import com.womeng.entity.UserSpace;
import com.womeng.entity.dto.UserDTO;
import com.womeng.entity.info.EmailVerifyInfo;
import com.womeng.entity.info.ResetPwdInfo;
import com.womeng.service.impl.UserServiceImpl;
import com.womeng.utils.AliOSSUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    // 注入阿里云oss工具类
    private final AliOSSUtils aliOSSUtils;

    /**
     * 用户登出接口
     * @param request HTTP请求
     * @return 统一响应结果
     */
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request){
        userService.logout(request);
        return Result.success();
    }

    /**
     * 获取用户头像
     * @param request HTTP请求
     * @return 统一响应结果
     */
    @GetMapping("/avatar")
    public Result getAvatar(HttpServletRequest request){
        String url = userService.getAvatar(request);
        return Result.success(url);
    }

    /**
     * 获取用户云盘空间使用情况
     * @param request　HTTP请求
     * @return 统一响应结果
     */
    @GetMapping("/space")
    public Result getSpace(HttpServletRequest request){
       UserSpace space = userService.getSpace(request);
        return Result.success(space);
    }

    @PostMapping("updateAvatar")
    public Result updateAvatar(@RequestBody UserDTO userDTO) throws IOException {
        return userService.updateAvatar(userDTO);
    }

    /**
     * 修改用户密码
     * @param info 包含要重置密码账户的email和newPwd属性
     * @return 统一响应结果
     */
    @PostMapping("/updatePwd")
    public Result updatePwd(@RequestBody ResetPwdInfo info){
        return userService.resetPwd(info);
    }

    /**
     * 检查修改密码的验证码
     * @param info 用户提交的验证信息
     * @return 统一响应结果
     */
    @PostMapping("/verifyUpdateCaptcha")
    public Result verifyUpdateCaptcha(@RequestBody EmailVerifyInfo info){
        return userService.verifyResetCaptcha(info);
    }

}
