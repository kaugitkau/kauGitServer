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

    @PostMapping("/like/{postId}")               //좋아요
    public void checkLikeButton(@PathVariable Long postId,
                                @Login SessionUser sessionUser) {
        Long userId = sessionUser.getUserId();
        likesCommandService.checkLikeStatus(userId, postId);
    }



}
