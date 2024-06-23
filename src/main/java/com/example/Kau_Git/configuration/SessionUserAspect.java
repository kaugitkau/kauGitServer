package com.example.Kau_Git.configuration;

import com.example.Kau_Git.Oauth.SessionUser;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SessionUserAspect {

    private final HttpSession httpSession;

    public SessionUserAspect(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Before("execution(* com.example.Kau_Git.Controller.*.*(..))")
    public void addSessionUserToModel() {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            System.out.println("사용자 정보: " + user.getName());
        }
    }
}