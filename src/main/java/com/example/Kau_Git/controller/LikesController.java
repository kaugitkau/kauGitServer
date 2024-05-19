package com.example.Kau_Git.controller;

import com.example.Kau_Git.Oauth.Login;
import com.example.Kau_Git.Oauth.SessionUser;
import com.example.Kau_Git.service.LikesCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikesController {

    private final LikesCommandService likesCommandService;

    //좋아요 - 좋아요 버튼을 누른 경우
    @PostMapping("/like/{postId}")
    public void checkLikeButton(@PathVariable Long postId,
                                @Login SessionUser sessionUser) {
        //User의 ID를 가져옴
        Long userId = sessionUser.getUserId();
        //user가 좋아요 버튼을 누름 -> 현재 상태(좋아요를 눌렀는지 여부)의 반대로 설정
        likesCommandService.checkLikeStatus(userId, postId);
    }



}
