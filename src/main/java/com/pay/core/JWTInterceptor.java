package com.pay.core;

import com.pay.domain.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JWTInterceptor implements HandlerInterceptor {

    private static final String HEADER = "PAY-AUTH-TOKEN";

    @Autowired
    AuthService authService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = request.getHeader(HEADER);

        if (authService.isUser(token))
            return true;

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

}
