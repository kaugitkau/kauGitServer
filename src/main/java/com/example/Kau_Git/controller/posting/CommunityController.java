package com.example.Kau_Git.controller.posting;

import com.example.Kau_Git.Oauth.Login;
import com.example.Kau_Git.Oauth.SessionUser;
import com.example.Kau_Git.dto.community.CommunityRequestDto;
import com.example.Kau_Git.dto.community.CommunityResponseDto;
import com.example.Kau_Git.service.PostingService;
import com.example.Kau_Git.service.posting.CommunityCommandService;
import com.example.Kau_Git.service.posting.CommunityQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityCommandService communityCommandService;
    private final CommunityQueryService communityQueryService;
    private final PostingService postingService;


    //커뮤니티 글 등록
    @PostMapping("/community")
    public void addCommunityPost(@RequestBody CommunityRequestDto.AddPostingDto addPostingDto,
                                 @Login SessionUser sessionUser) {

        Long userId = sessionUser.getUserId();
        communityCommandService.addPosting(userId, addPostingDto);

    }

    //커뮤니티 글 상세내용 조회
    @GetMapping("/community/{postingId}")
    // 컴파일 시에는 debugging enabled가 되어야 스프링이 찾을 수 있다.
    public CommunityResponseDto.PostingDto showPost(@PathVariable(name = "postingId") Long postingId) {

        return communityQueryService.showPosting(postingId);
    }

    //커뮤니티 글 목록 조회
    @GetMapping("/community/allpost")
    public CommunityResponseDto.ListDto showAllPost() {
        return communityQueryService.showList();
    }


    @PostMapping("/{id}/disLike")
    public void decrementLike(@PathVariable(name = "id") Long id) {
        postingService.decrementLikeCount(id);
    }

}
