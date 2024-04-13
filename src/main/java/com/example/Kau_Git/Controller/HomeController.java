package com.example.Kau_Git.Controller;

import com.example.Kau_Git.Oauth.Login;
import com.example.Kau_Git.Oauth.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    //private final PostsService postsService;
    @GetMapping("/")
    public String home(@Login SessionUser user, Model model) {
        // 세션에 저장된 값이 있을 때만 model에 userName 등록
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "home";
    }
}