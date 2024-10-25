package com.womeng.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtils {

    private static String signKey;

    @Value("${jwtutils.signKey}")
    public void setSignKey(String signKey){
        JwtUtils.signKey = signKey;
    }

    // 生成令牌
    public static String generateJwt(Map<String, Object> claims ,Long expire) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setClaims(claims) // 自定义内容
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
    }

    // 解析令牌 负载
    public static Claims ParseJwt(String jwt) {
        return Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
