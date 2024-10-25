package com.womeng.controller;

import com.womeng.entity.Result;
import com.womeng.entity.info.CaptchaVO;
import com.womeng.service.impl.CaptchaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/captcha")
public class CaptchaController {
    // 注入captchaService
    private final CaptchaService captchaService;

    /**
     * 获取图片验证码
     * @return 统一响应结果
     * @throws Exception
     */
    @GetMapping("/circle")
    public Result getCaptcha() throws Exception {
        CaptchaVO captcha = captchaService.getCaptcha();
        return Result.success(captcha);
    }
}
