package com.example.agentportalbackend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
@Slf4j
public class JwtTokenInterceptor extends HandlerInterceptorAdapter {
    private String SECRET_KEY = "dshhsfhshdsdfgasdfasdadasdfagsfbhfgjfhjgdhfsfgbdafaf";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            AccessTokenRequired annotation = method.getAnnotation(AccessTokenRequired.class);

            if (annotation != null) {
                String accessToken = retrieveAccessToken(request);
                if (accessToken != null && validateToken(accessToken)) {
                    // Token is valid, continue with the request
                    return true;
                } else {
                    // Token is invalid or not provided, handle unauthorized response
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return false;
                }
            }
        }

        return true;
    }

    private String retrieveAccessToken(HttpServletRequest request) {
        return request.getHeader("token");
    }

    private boolean validateToken(String token) {
        try {
            // Parse and validate the token
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            // Perform additional validation if needed

            return true;
        } catch (Exception e) {
            // Token is invalid or malformed
            log.info("error {}",e.getMessage());
            return false;
        }
    }
}
