package com.example.Kau_Git.configuration;

import com.example.Kau_Git.Oauth.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionUserInterceptor implements HandlerInterceptor {

    private final HttpSession httpSession;

    public SessionUserInterceptor(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            request.setAttribute("user", user);
            System.out.println("사용자 정보: " + user.getName());
        }
        return true;
    }
}
