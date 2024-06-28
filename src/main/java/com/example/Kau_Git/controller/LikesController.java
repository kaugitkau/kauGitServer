package com.example.Kau_Git.controller;


import com.example.Kau_Git.Oauth.Login;
import com.example.Kau_Git.Oauth.SessionUser;
import com.example.Kau_Git.dto.LikeRequestDto;
import com.example.Kau_Git.service.LikesCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/likeStatus")//좋아요가 눌려있는지 판단. 이미 좋아요가 되어있다면 프론트에서 눌려있게 표시해야하니까
    public Boolean checkLikeStatus(@RequestParam LikeRequestDto likeRequestDto, @Login SessionUser sessionUser) {

        likeRequestDto.setUserId(sessionUser.getUserId());
        return likesCommandService.isLiked(likeRequestDto);
    }


}
