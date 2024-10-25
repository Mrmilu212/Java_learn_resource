package com.womeng.entity.info;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 接口：/user/reset
 * 描述：封装用户提交的重置密码相关信息
 * 属性：对应User类中的属性
 */
@Data
@AllArgsConstructor
public class ResetPwdInfo {

    // 要重置密码账户的email
    private String email;
    // 新密码
    private String newPwd;
}
