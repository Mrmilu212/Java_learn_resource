package com.womeng.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述：验证码类
 * 属性：验证码在Redis中的key以及value
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Captcha {

    // 验证码在Redis中的key
    private String Key;

    // 验证码的值
    private String value;

}
