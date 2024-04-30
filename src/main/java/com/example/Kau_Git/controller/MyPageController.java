package com.example.Kau_Git.controller;

import com.example.Kau_Git.Oauth.Login;
import com.example.Kau_Git.Oauth.SessionUser;
import com.example.Kau_Git.dto.MyPageDto;
import com.example.Kau_Git.entity.ApplicantRespondent;
import com.example.Kau_Git.service.DeleteService;
import com.example.Kau_Git.service.GuideCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.Kau_Git.Service.MyPageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyPageController {
    private final GuideCommandService guideCommandService;
    private final MyPageService myPageService;
    private final DeleteService deleteService;

    @GetMapping("/myinfo")
    public MyPageDto.MyPageMyInfo getMyInfo(@Login SessionUser sessionUser) {
        Long userId = sessionUser.getUserId();
        MyPageDto.MyPageMyInfo myInfo = myPageService.getMyInfo(userId);
        return myInfo;
    }

    @GetMapping("/my-pheeds")
    public MyPageDto.ListPheedDto getMyPheeds(@Login SessionUser sessionUser) {
        Long userId = sessionUser.getUserId();
        MyPageDto.ListPheedDto pheeds = myPageService.getPheeds(userId);
        return pheeds;
    }


    @PostMapping("/guide/acceptMatch/{matchingId}")  //목록에서 하나 골라서 매칭 수락
    public void acceptMatching(Long matchingId) {

        guideCommandService.acceptMatching(matchingId);

    }

    @GetMapping("/guide/all-match")               //신청목록 전체 반환
    public List<ApplicantRespondent> getMatches(@PathVariable Long guideId) {
        List<ApplicantRespondent> matchingList = guideCommandService.getMatchingList(guideId);
        return matchingList;
    }

    @DeleteMapping("/delete-posting/{postingId}")
    public void deletePosting(@PathVariable Long postingId) {
        deleteService.deletePosting(postingId);
    }
}
