package com.example.Kau_Git.controller;

import com.example.Kau_Git.Oauth.Login;
import com.example.Kau_Git.Oauth.SessionUser;
import com.example.Kau_Git.dto.guide.GuideRequestDto;
import com.example.Kau_Git.dto.guide.GuideResponseDto;
import com.example.Kau_Git.entity.ApplicantRespondent;
import com.example.Kau_Git.service.GuideCommandService;
import com.example.Kau_Git.service.GuideQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MentoringController {
    private final GuideCommandService guideCommandService;
    private final GuideQueryService guideQueryService;

    private final Long testId = 1L; //로컬 원시 변수에 final로 선언하면 한번 초기화된 변수는 변경할 수 없는 상수값이 된다.

    @PostMapping("/guide/regist")        //가이드가 모집글 작성
    public void registGuidePosting(@RequestBody GuideRequestDto.RegistGuidePostingDto registGuidePostingDto) {
        guideCommandService.registGuiding(registGuidePostingDto, testId);
//포인트 얼마 이상 아니면 exception발생하게 하자.
    }

    @GetMapping("/guide/{postingId}")     //가이드가 작성한 모집글 상세내용 조회
    public GuideResponseDto.ShowPostingDto showPost(@PathVariable Long postingId) {
        GuideResponseDto.ShowPostingDto showPostingDto = guideQueryService.showPost(postingId);
        return showPostingDto;
    }

    @GetMapping("/guide/allpost")        //가이드들이 작성한 모집글을 조회
    public GuideResponseDto.ShowAllPostDto showAllPost(@Login SessionUser sessionUser) {
        GuideResponseDto.ShowAllPostDto showAllPostDto = guideQueryService.showAllPost();
        System.out.println(sessionUser.getUserId());
        return showAllPostDto;
    }


    @PostMapping("guide/match/{applicantId}/{guideId}")  //가이드가 작성한 글을 보고 멘티가 신청
    public void applyMatching(@PathVariable Long applicantId, @PathVariable Long guideId) {

        guideCommandService.applyMatching(applicantId, guideId);
    }

    @PostMapping("guide/acceptMatch/{matchingId}")  //목록에서 하나 골라서 매칭 수락
    public void acceptMatching(Long matchingId) {
        guideCommandService.acceptMatching(matchingId);

    }

    @GetMapping("guide/all-match")               //신청목록 전체 반환
    public List<ApplicantRespondent> getMatches(@PathVariable Long guideId) {
        List<ApplicantRespondent> matchingList = guideCommandService.getMatchingList(guideId);
        return matchingList;
    }
}
