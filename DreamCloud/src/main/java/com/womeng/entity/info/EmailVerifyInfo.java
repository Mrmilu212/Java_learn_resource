package com.womeng.entity.info;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口：/user/loginByEmail
 * 描述：封装用户提交的邮箱登录相关信息
 * 属性：登录的email以及验证码
 */
@Data
@NoArgsConstructor
public class EmailVerifyInfo {

    // 目标邮箱
    private String email;

    // 验证码
    private String captcha;


}
