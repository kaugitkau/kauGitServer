package com.example.Kau_Git.controller;

import com.example.Kau_Git.Oauth.Login;
import com.example.Kau_Git.Oauth.OAuthAttributes;
import com.example.Kau_Git.Oauth.SessionUser;
import com.example.Kau_Git.service.GetFestivalService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final GetFestivalService gs;

    //서버 배포 되는지 테스트용
    @GetMapping("/test")
    public String test() {
        return "index";
    }

    @GetMapping("/login")
    public String login(@RequestParam("provider") String provider, Model model) {
        // provider에 따라 네이버 또는 구글 로그인 페이지로 리다이렉트
        String redirectUrl = "redirect:/";
        if ("naver".equals(provider)) {
            redirectUrl = "redirect:/oauth2/authorization/naver"; // 네이버 로그인 처리 URL
        } else if ("google".equals(provider)) {
            redirectUrl = "redirect:/oauth2/authorization/google"; // 구글 로그인 처리 URL
        }
        return redirectUrl;
    }

    @GetMapping("/")
    public String home(@Login SessionUser user, Model model) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        System.out.println("로그아웃해야죠22");

        List<JSONObject> festivals = gs.getFestival();
        model.addAttribute("festivals",festivals);

        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("로그아웃해야죠");
        // Spring Security 로그아웃
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
