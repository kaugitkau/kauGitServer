package com.example.Kau_Git.controller;

import com.example.Kau_Git.service.LikesCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikesController {

    private final LikesCommandService likesCommandService;

    private final Long testId = 1L;

    @PostMapping("/like/{postId}")               //좋아요
    public void checkLikeButton(@PathVariable Long postId) {
        likesCommandService.likePosting(testId, postId);
    }

    @PostMapping("/cancel-like/{postId}")         //좋아요취소
    public void uncheckLikeButton(@PathVariable Long postId) {
        likesCommandService.cancelLike(testId, postId);
    }


}
