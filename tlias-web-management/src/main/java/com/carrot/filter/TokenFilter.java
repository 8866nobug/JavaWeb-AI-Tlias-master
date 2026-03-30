package com.carrot.filter;


import com.carrot.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Value("${tokenFilter.loginPath}")
    private String loginPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("DemoFilter init");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("DemoFilter doFilter");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1.获取请求路径
        String requestURI = request.getRequestURI();
        // 2.判断是否登录请求，路径包含/login，说明是登录操作，方向
        if (requestURI.contains(loginPath)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 3.获取token
        String token = request.getHeader("token");
        // 4.判断token是否为空
        if (token == null || token.isEmpty()) {
            log.info("token为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        // 5.验证token
        try {
            Claims claims = JwtUtils.parseToken(token);
            log.info("令牌合法");
        } catch (Exception e) {
            log.info("令牌非法，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        log.info("DemoFilter destroy");
        Filter.super.destroy();
    }
}
