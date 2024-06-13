package com.example.Kau_Git.Oauth;

import com.example.Kau_Git.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig { // 시큐리티 환경 설정 클래스

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                /*.headers(headers -> headers
                        .contentSecurityPolicy("script-src 'self'; frame-ancestors 'self';"))
                */
                .authorizeHttpRequests((author)->author
                        .requestMatchers( "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile", "/test" , "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/v1/**").hasRole("USER")
                        .requestMatchers("/community").authenticated() // /community 경로는 인증 필요
                        .requestMatchers("/community/**").permitAll() // /community/ 하위 경로는 접근 허용
                        .anyRequest().authenticated())
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 처리 URL 명시적 설정 (선택적)
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                        .defaultSuccessUrl("http://localhost:3000/", true));
        return http.build();
    }

}
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//    private final CustomOAuth2UserService customOAuth2UserService;
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
//                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
//                .oauth2Login(oauth2 -> oauth2
//                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
//                        .defaultSuccessUrl("/", true));
//
//        ;
//        return http.build();
//    }
//}


