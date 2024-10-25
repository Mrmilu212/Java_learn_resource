package com.womeng.entity.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 接口：/login/login
 * 描述： 封装用户提交的登录信息
 * 属性：username和email只能有一个为空
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {

    // 邮箱
    private String email;
    // 用户名
    private String username;
    // 登录密码
    private String password;
}
