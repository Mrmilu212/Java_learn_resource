package com.womeng.config;

import com.womeng.intercptor.LoginCheckInterceptor;
import com.womeng.intercptor.PasswordInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final LoginCheckInterceptor loginCheckInterceptor;

    private final PasswordInterceptor passwordInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/login/**", "/captcha/**", "/email/**", "/file/**", "/trashBin/**");

        registry.addInterceptor(passwordInterceptor).addPathPatterns("/login/resetPwd", "/user/updatePwd");
    }

    /**
     * 允许跨域请求
     *
     * @param registry registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(true)
                //放行哪些原始域
                // TODO 将访问白名单改为从配置文件获取或直接在配置文件开启
                .allowedOrigins("http://localhost:5173") // 访问源（白名单）
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .exposedHeaders("*")
                .maxAge(3 * 60 * 60 * 1000); // 设置超时时延
    }
}
