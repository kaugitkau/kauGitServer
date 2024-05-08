package com.example.Kau_Git.controller;

import com.example.Kau_Git.entity.User;
import com.example.Kau_Git.Oauth.Login;
import com.example.Kau_Git.Oauth.SessionUser;
import com.example.Kau_Git.Service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InformationController {

    private final MyPageService myPageService;

    @GetMapping("/MyInformation")
    public ResponseEntity<?> MyInfo(@Login SessionUser user){
        if(user != null){
            User userInfo = myPageService.GetInformation(user.getEmail(), user.getName());
            if(userInfo != null){
                return ResponseEntity.ok().body(userInfo);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자 정보를 찾을 수 없습니다.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).header("Location", "/api/login").build();
        }
    }

    @PostMapping("/MyInformation")
    public ResponseEntity<?> updateInformation(@Login SessionUser user,
                                               @RequestParam("address") String address,
                                               @RequestParam("religion") String religion,
                                               @RequestParam("nationality") String nationality,
                                               @RequestParam("gender") int gender){
        if(user != null){
            myPageService.SetInformation(user.getEmail(), address, religion, nationality, gender);
            return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).header("Location", "/api/MyInformation").build();
        } else {
            return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).header("Location", "/api/login").build();
        }
    }
}
