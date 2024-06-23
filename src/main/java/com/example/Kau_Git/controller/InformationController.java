package com.example.Kau_Git.controller;

import com.example.Kau_Git.Oauth.Login;
import com.example.Kau_Git.Oauth.SessionUser;
import com.example.Kau_Git.service.MyPageService;
import com.example.Kau_Git.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class InformationController { // 안쓰임

    private final MyPageService myPageService;

    @GetMapping("/MyInformation")
    public String MyInfo(@Login SessionUser user, Model model){
        if(user != null){
            // 사용자 이메일을 기반으로 정보 가져오기
            User userInfo = myPageService.GetInformation(user.getEmail(), user.getName());
            if(userInfo != null){
                // 모델에 사용자 정보 추가
                model.addAttribute("user", userInfo);
            } else {
                // 사용자 정보를 찾을 수 없는 경우의 처리
                // 이 부분은 필요에 따라 적절한 메시지 또는 로직으로 대체할 수 있습니다.
                model.addAttribute("errorMessage", "사용자 정보를 찾을 수 없습니다.");
            }
        } else {
            // 로그인되지 않은 경우의 처리
            // 이 부분은 필요에 따라 적절한 메시지 또는 로직으로 대체할 수 있습니다.
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        return "MyInformation"; // 사용자 정보를 표시할 뷰의 이름 반환
    }

    @PostMapping("/MyInformation")
    public String updateInformation(@Login SessionUser user,
                                    @RequestParam("address") String address, // 명시적으로 이름 지정 해줘야함. 지금은 4개 다 집어넣어야 바뀌는데 추후 개별수정 넣어야함.
                                    @RequestParam("religion") String religion,
                                    @RequestParam("nationality") String nationality,
                                    @RequestParam("gender") int gender){

        if(user != null){
            myPageService.SetInformation(user.getEmail(), address, religion, nationality, gender);
            return "redirect:/MyInformation"; // 정보 업데이트 후, 사용자 정보 페이지로 리다이렉션
        } else {
            return "redirect:/login";
        }
    }
}
