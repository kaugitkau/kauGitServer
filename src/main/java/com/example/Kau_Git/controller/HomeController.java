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
    /*
    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getLoginUrl(@RequestParam("provider") String provider) {
        String loginUrl = "";
        if ("naver".equals(provider)) {
            loginUrl = "/oauth2/authorization/naver";
        } else if ("google".equals(provider)) {
            loginUrl = "/oauth2/authorization/google";
        }
        Map<String, String> response = new HashMap<>();
        response.put("loginUrl", loginUrl);
        return ResponseEntity.ok(response);
    }
     */

    @GetMapping("/login")
    public RedirectView getLoginUrl(@RequestParam("provider") String provider, HttpServletResponse response) {
        String clientId = "";
        String redirectUri = "";
        String authorizationUri = "";
        Set<String> scopes = new HashSet<>();

        if ("naver".equals(provider)) {
            clientId = "ud72bzHYNtmwGhKFDjAU";
            redirectUri = "http://localhost:8080/login/oauth2/code/naver";
            authorizationUri = "https://nid.naver.com/oauth2.0/authorize";
            scopes.add("profile");
            scopes.add("email");
        } else if ("google".equals(provider)) {
            clientId = "8462816831-3lop9fop0m4utavosv1jns6t6g66qnt9.apps.googleusercontent.com";
            redirectUri = "http://localhost:8080/login/oauth2/code/google";
            authorizationUri = "https://accounts.google.com/o/oauth2/auth";
            scopes.add("profile");
            scopes.add("email");
        }

        OAuth2AuthorizationRequest authorizationRequest = OAuth2AuthorizationRequestCreator.createAuthorizationRequest(clientId, redirectUri, authorizationUri, scopes);
        CookieUtils.addCookie(response, "oauth2_auth_request", CookieUtils.serialize(authorizationRequest), 180);
        String authorizationRequestUri = authorizationRequest.getAuthorizationRequestUri();
        return new RedirectView(authorizationRequestUri);
    }
    @GetMapping("/login/oauth2/code/{provider}")
    public String handleOAuth2Callback(@PathVariable("provider") String provider, @RequestParam("code") String code, @RequestParam("state") String state, HttpServletRequest request, HttpServletResponse response) {
        Optional<Cookie> cookieOptional = CookieUtils.getCookie(request, "oauth2_auth_request");
        if (cookieOptional.isPresent()) {
            OAuth2AuthorizationRequest authorizationRequest = CookieUtils.deserialize(cookieOptional.get(), OAuth2AuthorizationRequest.class);
            // 인증 요청을 처리하고 사용자 정보를 가져오는 로직
            CookieUtils.deleteCookie(request, response, "oauth2_auth_request");
        }
        return "redirect:/";
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

    @GetMapping("/topmentors")
    @ResponseBody
    public TopService.ListTopMentorsDto getTopMentors(){
        TopService.ListTopMentorsDto topMentors = ts.findTopMentors();
        return topMentors;


    }

    @GetMapping("/tophousings")
    @ResponseBody
    public TopService.ListTopHousingsDto getTopHousings(){
        TopService.ListTopHousingsDto topHousings = ts.findTopHousings();
        return topHousings;
    }

    @GetMapping("/hotpostings")
    @ResponseBody
    public TopService.ListHotCommunityPostingsDto getHotPostings(){
        TopService.ListHotCommunityPostingsDto hotPostings = ts.findHotPostings();
        return hotPostings;
    }


}
