package com.example.Kau_Git.controller;

import com.example.Kau_Git.Oauth.CookieUtils;
import com.example.Kau_Git.Oauth.Login;
import com.example.Kau_Git.Oauth.OAuth2AuthorizationRequestCreator;
import com.example.Kau_Git.Oauth.SessionUser;
import com.example.Kau_Git.service.GetFestivalService;
import com.example.Kau_Git.service.TopService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HomeController {

    private final GetFestivalService gs;
    private final TopService ts;

    @GetMapping("/test")
    public String test() {
        return "index";
    }


    @GetMapping("/")
    public ResponseEntity<?> home(@Login SessionUser user) {
        if (user != null) {
            List<JSONObject> festivals = gs.getFestival();
            return ResponseEntity.ok().body(festivals);
        } else {
            return ResponseEntity.noContent().build();
        }

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

    @GetMapping("/login-status")
    public String loginStatus() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication.getPrincipal() instanceof String)) {
            return "Logged In";
        } else {
            return "Not Logged In";
        }
    }

    //멘토링(버디에서) 평점이 높은 유저들을 추천
    @GetMapping("/topmentors")
    @ResponseBody
    public TopService.ListTopMentorsDto getTopMentors(){
        TopService.ListTopMentorsDto topMentors = ts.findTopMentors();
        return topMentors;


    }

    //쉐어링평점이 높은 유저들을 추천
    @GetMapping("/tophousings")
    @ResponseBody
    public TopService.ListTopHousingsDto getTopHousings(){
        TopService.ListTopHousingsDto topHousings = ts.findTopHousings();
        return topHousings;
    }

    //인기있는 포스팅을 조회
    @GetMapping("/hotpostings")
    @ResponseBody
    public TopService.ListHotCommunityPostingsDto getHotPostings(){
        TopService.ListHotCommunityPostingsDto hotPostings = ts.findHotPostings();
        return hotPostings;
    }


}
