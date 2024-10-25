package com.womeng.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.womeng.entity.Result;
import com.womeng.entity.Captcha;
import com.womeng.entity.info.CaptchaVO;
import com.womeng.utils.RedisKeyUtils;
import com.womeng.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CaptchaService {


    private final RedisUtil redisUtil;  // 注入 RedisUtil


    /**
     * 图片验证码生成接口
     * @return Map 封装验证码在Redis中的key，以及Base64 格式的图片验证码
     * @throws Exception
     */
    public CaptchaVO getCaptcha() throws Exception {
        // 生成唯一键，使用 UUID 确保每个验证码唯一
        String key = RedisKeyUtils.initKey(RedisKeyUtils.CAPTCHA,UUID.randomUUID().toString());
        return generateCaptcha(key);
    }


    /**
     * @param captcha 验证码类：包含key和value
     * @return Result 空值，表示验证成功；否则验证失败
     */
    public Result verifyCaptcha(Captcha captcha) {

        String captchaValue = captcha.getValue();

        if (captchaValue == null){
            return Result.error("验证码不能为空");
        }

        // 从 Redis 中获取存储的验证码
        String storedCaptcha = (String) redisUtil.get(captcha.getKey());

        if (storedCaptcha == null) {
            // 验证码不存在或已过期
            return Result.error("验证码不存在或已过期");
        }

        if (!storedCaptcha.equalsIgnoreCase(captchaValue)) {
            // 验证码不匹配
            return Result.error("验证码错误");
        }

        return Result.success();
    }

    /**
     * 生成图片验证码
     * @param key 用于指定Redis中验证码的key
     * @return 封装好的验证码数据
     * @throws IOException
     */
    private CaptchaVO generateCaptcha(String key) throws IOException {
        // 1.生成验证码
        // 生成验证码，设置宽度、高度、验证码字符数和干扰圆圈数
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(100, 50, 4, 20);
        // 创建一个字节数组输出流来保存图片数据
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // 将验证码图片写入到输出流
        captcha.write(outputStream);
        // 将输出流中的字节数据转换为 Base64 字符串
        String base64CaptchaImage = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        // 关闭流
        outputStream.close();

        // 2.封装和存储验证码
        // 将验证码的 key 和 Base64 格式的图片返回
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setKey(key);
        captchaVO.setImg("data:image/png;base64," + base64CaptchaImage);
        // 将验证码的 code 存储在 Redis 中，设置过期时间（2分钟 = 120秒）
        redisUtil.set(key, captcha.getCode(), 120);

        // 3.返回
        return captchaVO;
    }
}
