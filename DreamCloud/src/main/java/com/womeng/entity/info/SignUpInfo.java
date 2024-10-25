package com.womeng.entity.info;

import com.womeng.entity.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口：/user/signup
 * 描述：封装用户提交的注册相关信息
 * 属性：包含验证码的key和value以及用户的注册数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpInfo {
    // 验证码文本
    private String captchaValue;
    // 用户填写的用户注册信息
    private UserDTO userDTO;
}
