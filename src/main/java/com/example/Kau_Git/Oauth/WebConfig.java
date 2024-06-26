package com.example.Kau_Git.Oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {//

    private final com.example.Kau_Git.configuration.SessionUserInterceptor sessionUserInterceptor;

    @Autowired
    public WebConfig(com.example.Kau_Git.configuration.SessionUserInterceptor sessionUserInterceptor) {
        this.sessionUserInterceptor = sessionUserInterceptor;
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://172.30.1.52:3000","http://localhost:3000", "http://parkingzone.shop", "http://parkingzone.shop:3000", "http://parkingzone.shop:80") // 리액트 앱의 URL로 변경
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true); // 쿠키를 포함하여 요청을 허용
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginUserArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionUserInterceptor).addPathPatterns("/**");
    }

}


