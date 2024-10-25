package com.womeng.intercptor;

import com.alibaba.fastjson.JSONObject;
import com.womeng.entity.Result;
import com.womeng.utils.JwtUtils;
import com.womeng.utils.RedisKeyUtils;
import com.womeng.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class PasswordInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求的url：{}", url);
        // 2.获取请求头中的令牌（tokenTemp）
        String tokenTemp = request.getHeader("tokenTemp");

        // 3.判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasLength(tokenTemp)) {
            log.info("临时token为空，不允许修改");
            Result error = Result.error("NO_PERMITTED");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }
        // 用户获取解析后的数据
        Claims claims;
        // 4.解析token，如果解析失败，返回错误结果（未登录）
        try {
            claims = JwtUtils.ParseJwt(tokenTemp);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("临时token非法，不允许修改");
            Result error = Result.error("NO_PERMITTED");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        // 通过解析出来的Claims，获取用户名
        String email = claims.get("email").toString();
        String captcha = claims.get("captcha").toString();
        // 查询临时令牌是否过期
        String  jwt = redisUtil.get(RedisKeyUtils.initTempKey(email,captcha)).toString();

        // 查询到的结果为空，或jwt不相等
        if (jwt == null || !jwt.equals(tokenTemp)){
            log.info("临时token已失效，不允许修改");
            Result error = Result.error("NO_PERMITTED");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }
        // 5.放行
        log.info("令牌合法，允许修改");
        return true;
    }
}
