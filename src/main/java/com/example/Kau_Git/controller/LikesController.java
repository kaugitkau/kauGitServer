package com.example.Kau_Git.controller;


import com.example.Kau_Git.Oauth.Login;
import com.example.Kau_Git.Oauth.SessionUser;
import com.example.Kau_Git.dto.LikeRequestDto;
import com.example.Kau_Git.service.LikesCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikesController {

    private final LikesCommandService likesCommandService;

    //좋아요 저장(안되어있으면 좋아요 처리하고, 이미 되어있다면 취소)
    @PostMapping("/like") // postid,userid 입력  
    public void checkLikeButton(@RequestBody LikeRequestDto likeRequestDto,
                                @Login SessionUser sessionUser) {
        likeRequestDto.setUserId(sessionUser.getUserId());
        likesCommandService.checkLikeStatus(likeRequestDto);
    }
}
