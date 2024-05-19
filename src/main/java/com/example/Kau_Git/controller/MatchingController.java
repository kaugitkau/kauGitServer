package com.example.Kau_Git.controller;

import com.example.Kau_Git.service.posting.GuideCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MatchingController {
    private final GuideCommandService guideCommandService;

    //가이드가 작성한 글을 보고 멘티가 신청
    @PostMapping("guide/match/{applicantId}/{guideId}")
    public void applyMatching(@PathVariable Long applicantId, @PathVariable Long guideId) {

        guideCommandService.applyMatching(applicantId, guideId);
    }

}
