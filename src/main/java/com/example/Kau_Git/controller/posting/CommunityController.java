package com.example.Kau_Git.controller.posting;

import com.example.Kau_Git.Oauth.Login;
import com.example.Kau_Git.Oauth.SessionUser;
import com.example.Kau_Git.dto.community.CommunityRequestDto;
import com.example.Kau_Git.dto.community.CommunityResponseDto;
import com.example.Kau_Git.service.posting.CommunityCommandService;
import com.example.Kau_Git.service.posting.CommunityQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityCommandService communityCommandService;
    private final CommunityQueryService communityQueryService;


    //글 등록
    @PostMapping("/community")
    public ResponseEntity<?> addCommunityPost(@RequestBody CommunityRequestDto.AddPostingDto addPostingDto,
                                              @Login SessionUser sessionUser) {
        try {
            Long userId = sessionUser.getUserId();
            communityCommandService.addPosting(userId, addPostingDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("커뮤니티 글 등록 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    //글 조회
    @GetMapping("/community/{postingId}")
    public ResponseEntity<?> showPost(@PathVariable(name = "postingId") Long postingId) {
        try {
            CommunityResponseDto.PostingDto postingDto = communityQueryService.showPosting(postingId);
            return ResponseEntity.ok(postingDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("커뮤니티 글 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 인기순으로 조회
    @GetMapping("/community/allpost-popularity")
    public ResponseEntity<?> showAllPost() {
        try {
            CommunityResponseDto.ListDto listDto = communityQueryService.showList("popularity");
            return ResponseEntity.ok(listDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("커뮤니티 글 목록 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 최신순으로 조회
    @GetMapping("/community/allpost-latest")
    public ResponseEntity<?> showAllPostLatest() {
        try {
            CommunityResponseDto.ListDto listDto = communityQueryService.showList("latest");
            return ResponseEntity.ok(listDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("커뮤니티 글 목록 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

}