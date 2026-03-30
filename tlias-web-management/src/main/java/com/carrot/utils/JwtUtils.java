package com.carrot.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    
    // 密钥，与测试类保持一致
    private static final String SECRET_KEY = "Y2Fycm90";
    
    // 过期时间：12小时（单位：毫秒）
    private static final long EXPIRATION_TIME = 12 * 3600 * 1000;
    
    /**
     * 生成JWT令牌
     * @param claims 要包含在令牌中的自定义声明
     * @return 生成的JWT令牌字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        // 计算过期时间
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        
        // 构建并返回JWT令牌
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    
    /**
     * 解析JWT令牌
     * @param token 要解析的JWT令牌字符串
     * @return 令牌中包含的声明信息
     */
    public static Claims parseToken(String token) throws Exception {
        // 解析令牌并返回声明
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
