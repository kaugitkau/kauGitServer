package com.example.Kau_Git.controller.posting;

import com.example.Kau_Git.dto.community.CommunityRequestDto;
import com.example.Kau_Git.dto.community.CommunityResponseDto;
import com.example.Kau_Git.service.posting.CommunityCommandService;
import com.example.Kau_Git.service.posting.CommunityQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityCommandService communityCommandService;
    private final CommunityQueryService communityQueryService;


    private final Long testId = 1L;


    @PostMapping("/community")
    public void addSharingPost(@RequestBody CommunityRequestDto.AddPostingDto addPostingDto) {
//        SessionUser user = (SessionUser) httpSession.getAttribute("user"); //이건 일단 남겨놓음.
        communityCommandService.addPosting(testId, addPostingDto);

    }

    @GetMapping("/community/{postingId}")//오류발생: name과 변수명이 동일한 경우에는 생략 가능하지 않나? -> 일단 아닌걸로 하자.
    // 컴파일 시에는 debugging enabled가 되어야 스프링이 찾을 수 있다.
    public CommunityResponseDto.PostingDto showPost(@PathVariable(name = "postingId") Long postingId) {

        return communityQueryService.showPosting(postingId);
    }

    @GetMapping("/community/allpost")
    public CommunityResponseDto.ListDto showAllPost() {
        return communityQueryService.showList();
    }

}
