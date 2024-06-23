package com.example.Kau_Git.controller;

import com.example.Kau_Git.Oauth.Login;
import com.example.Kau_Git.Oauth.SessionUser;
import com.example.Kau_Git.dto.MyPageDto;
import com.example.Kau_Git.entity.ApplicantRespondent;
import com.example.Kau_Git.service.DeleteService;
import com.example.Kau_Git.service.posting.GuideCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Kau_Git.service.MyPageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyPageController {
    private final GuideCommandService guideCommandService;
    private final MyPageService myPageService;
    private final DeleteService deleteService;

    //마이페이지 나의 정보 조회 -ok
    @GetMapping("/myinfo")
    public MyPageDto.MyPageMyInfo getMyInfo(@Login SessionUser sessionUser) {
        Long userId = sessionUser.getUserId();
        MyPageDto.MyPageMyInfo myInfo = myPageService.getMyInfo(userId);
        return myInfo;
    }

    //마이페이지 내가 작성한 피드 목록 조회 -ok
    @GetMapping("/my-pheeds")
    public MyPageDto.ListPheedDto getMyPheeds(@Login SessionUser sessionUser) {
        Long userId = sessionUser.getUserId();
        MyPageDto.ListPheedDto pheeds = myPageService.getPheeds(userId);
        return pheeds;
    }

    @PostMapping("/guide/acceptMatch/{matchingId}")  // 목록에서 하나 골라서 매칭 수락 - ok
    public ResponseEntity<String> acceptMatching(@PathVariable Long matchingId) {
        try {
            guideCommandService.acceptMatching(matchingId);
            return ResponseEntity.ok("매칭이 수락 됐습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //가이드, 쉐어링 수락
    //가이드,쉐어링 요청목록 조회
    @GetMapping("/guide/all-match/{guideId}")//신청목록 전체 반환 -ok
    public ResponseEntity<?> getMatches(@PathVariable Long guideId) {
        try {
            List<ApplicantRespondent> matchingList = guideCommandService.getMatchingList(guideId);
            return ResponseEntity.ok(matchingList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
    //posting 삭제
    @DeleteMapping("/delete-posting/{postingId}")
    public ResponseEntity<String> deletePosting(@PathVariable Long postingId) {
        try {
            deleteService.deletePosting(postingId);
            return ResponseEntity.ok("삭제 됐습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
