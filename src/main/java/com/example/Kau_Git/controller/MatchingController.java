package com.example.Kau_Git.controller;

import com.example.Kau_Git.Oauth.Login;
import com.example.Kau_Git.Oauth.SessionUser;
import com.example.Kau_Git.dto.MatchingApplicationDto;
import com.example.Kau_Git.service.posting.GuideCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MatchingController {
    private final GuideCommandService guideCommandService;

    //가이드가 작성한 글을 보고 멘티가 신청 - ok.
    @PostMapping("guide/match")
    public ResponseEntity<Void> applyMatching(@RequestBody MatchingApplicationDto applicationDto, @Login SessionUser sessionUser) {
        if (applicationDto.getGuideId() == null) {
            return ResponseEntity.badRequest().build();
        }
        guideCommandService.applyMatching(sessionUser.getUserId(), applicationDto.getGuideId());
        return ResponseEntity.ok().build();
    }

}
