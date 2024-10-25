package com.womeng.entity.info;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * 用于封装图片验证码响应数据
 */

@Data
@RequiredArgsConstructor
public class CaptchaVO {

    //验证码在Redis中的key
    private String key;

    //验证码图片的base64编码
    private String img;
}
