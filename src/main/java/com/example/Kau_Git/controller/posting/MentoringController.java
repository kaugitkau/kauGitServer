package com.example.Kau_Git.controller.posting;

import com.example.Kau_Git.Oauth.Login;
import com.example.Kau_Git.Oauth.SessionUser;
import com.example.Kau_Git.dto.guide.GuideRequestDto;
import com.example.Kau_Git.dto.guide.GuideResponseDto;
import com.example.Kau_Git.service.posting.GuideCommandService;
import com.example.Kau_Git.service.posting.GuideQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MentoringController {
    private final GuideCommandService guideCommandService;
    private final GuideQueryService guideQueryService;

    //멘토링 모집글 등록
    @PostMapping("/guide")
    public void registGuidePosting(@RequestBody GuideRequestDto.RegistGuidePostingDto registGuidePostingDto, @Login SessionUser sessionUser) {
        Long userId = sessionUser.getUserId();
        guideCommandService.registGuiding(registGuidePostingDto, userId);
//포인트 얼마 이상 아니면 exception발생하게 하자.
    }

    //멘토링 모집글 상세내용 조회
    //가이드가 작성한 모집글 상세내용 조회
    @GetMapping("/guide/{postingId}")
    public GuideResponseDto.ShowPostingDto showPost(@PathVariable Long postingId) {
        GuideResponseDto.ShowPostingDto showPostingDto = guideQueryService.showPost(postingId);
        return showPostingDto;
    }

    //멘토링 모집글 목록 조회
    //가이드들이 작성한 모집글을 조회
    @GetMapping("/guide/allpost")
    public GuideResponseDto.ShowAllPostDto showAllPost(@Login SessionUser sessionUser) {
        GuideResponseDto.ShowAllPostDto showAllPostDto = guideQueryService.showAllPost();
        System.out.println(sessionUser.getUserId());
        return showAllPostDto;

    }


}
