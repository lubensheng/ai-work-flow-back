package com.example.aiworkflowback.interceptor;

import com.example.aiworkflowback.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler) throws Exception {
    // 从请求头中获取token
    String token = request.getHeader("Authorization");

    if (token == null || token.isEmpty()) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write("{\"code\":401,\"message\":\"缺少token，请先登录\"}");
      return false;
    }

    if (token.startsWith("Bearer ")) {
      token = token.substring(7);
    }

    try {
      // 验证token
      Claims claims = JwtUtil.getAllClaimsFromToken(token);
      String username = claims.getSubject();

      // 检查token是否过期
      if (JwtUtil.isTokenExpired(token)) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"code\":401,\"message\":\"token已过期，请重新登录\"}");
        return false;
      }

      // 将用户信息存入request，供后续使用
      request.setAttribute("username", username);
      request.setAttribute("claims", claims);

      return true;

    } catch (Exception e) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write("{\"code\":401,\"message\":\"token无效，请重新登录\"}");
      return false;
    }
  }
}
