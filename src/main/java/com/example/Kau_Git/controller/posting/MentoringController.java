package com.example.Kau_Git.controller.posting;

import com.example.Kau_Git.Oauth.Login;
import com.example.Kau_Git.Oauth.SessionUser;
import com.example.Kau_Git.dto.guide.GuideRequestDto;
import com.example.Kau_Git.dto.guide.GuideResponseDto;
import com.example.Kau_Git.service.posting.GuideCommandService;
import com.example.Kau_Git.service.posting.GuideQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MentoringController {
    private final GuideCommandService guideCommandService;
    private final GuideQueryService guideQueryService;

    // 멘토링 모집글 등록 , 에러처리 완료
    @PostMapping("/guide")
    public ResponseEntity<?> registGuidePosting(@RequestBody GuideRequestDto.RegistGuidePostingDto registGuidePostingDto, @Login SessionUser sessionUser) {
        try {
            Long userId = sessionUser.getUserId();

            // 포인트 체크
            if (!guideCommandService.checkUserPoints(userId, 50)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("멘토링 모집글을 등록하기 위해서는 50 포인트 이상이 필요합니다.");
            }

            guideCommandService.registGuiding(registGuidePostingDto, userId);
            return ResponseEntity.ok().body("멘토링 모집글이 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 멘토링 모집글 상세내용 조회
    @GetMapping("/guide/{postingId}")
    public ResponseEntity<?> showPost(@PathVariable Long postingId) {
        try {
            GuideResponseDto.ShowPostingDto showPostingDto = guideQueryService.showPost(postingId);
            return ResponseEntity.ok(showPostingDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("멘토링 모집글 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 멘토링 모집글 목록 조회
    //멘토링 모집글 목록 조회 -> 포스트 하면 M로 DB에 들어가는데 guideQueryService에서는 G로 찾고 있음. M으로 바꿀게
    @GetMapping("/guide/allpost")
    public ResponseEntity<?> showAllPost(@Login SessionUser sessionUser) {
        try {
            GuideResponseDto.ShowAllPostDto showAllPostDto = guideQueryService.showAllPost();
            System.out.println(sessionUser.getUserId());
            return ResponseEntity.ok(showAllPostDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("멘토링 모집글 목록 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}