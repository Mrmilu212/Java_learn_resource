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
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    @Override // 目标资源方法运行前运行，返回true，放行，返回false，不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求的url：{}", url);
        // 2.获取请求头中的令牌（token）
        String token = request.getHeader("token");

        // 3.判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasLength(token)) {
            log.info("请求token为空，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }
        // 用户获取解析后的数据
        Claims claims;
        // 4.解析token，如果解析失败，返回错误结果（未登录）
        try {
             claims = JwtUtils.ParseJwt(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("请求token非法，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        // 通过解析出来的Claims，获取用户名
        String email = claims.get("email").toString();
        // 查询令牌是否过期
        String jwt = (String) redisUtil.get(RedisKeyUtils.initKey(RedisKeyUtils.JWT,email));
        // 查询临时令牌
        if (jwt == null && url.contains("resetPwd")){ // 检查是否为重制密码的临时token
            // 这样做可以做到放行临时令牌以及防止临时令牌被恶意使用
            jwt = (String) redisUtil.get(RedisKeyUtils.initKey(RedisKeyUtils.TEMP_JWT,email));
        }
        // 查询到的结果为空，或jwt不相等
        if (jwt == null || !jwt.equals(token)){
            log.info("token已失效，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        // 5.放行
        log.info("令牌合法，放行");
        return true;
    }

    @Override // 目标资源方法运行后执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle ...");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override // 视图渲染完毕后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion ...");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
