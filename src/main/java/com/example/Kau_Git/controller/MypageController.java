package com.example.Kau_Git.controller;

import com.example.Kau_Git.entity.ApplicantRespondent;
import com.example.Kau_Git.service.GuideCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MypageController {
    private final GuideCommandService guideCommandService;


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
