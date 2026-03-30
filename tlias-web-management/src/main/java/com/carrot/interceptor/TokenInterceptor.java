package com.carrot.interceptor;

import com.carrot.utils.CurrentHolder;
import com.carrot.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取token
        String token = request.getHeader("token");
        // 2.判断token是否为空
        if (token == null || token.isEmpty()) {
            log.info("token为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        // 3.验证token
        try {
            Claims claims = JwtUtils.parseToken(token);
            log.info("令牌合法");
            Integer id = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(id);
            log.info("录入当前用户ID：{}", id);
        } catch (Exception e) {
            log.info("令牌非法，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        CurrentHolder.remove();
        log.info("清理当前用户ID");
    }

}
