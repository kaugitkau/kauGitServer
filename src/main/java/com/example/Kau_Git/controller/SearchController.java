package com.example.Kau_Git.controller;

import com.example.Kau_Git.service.PostingService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final PostingService postingService;

    // 제목으로 포스팅 조회
    @GetMapping("/search/title")
    public PostingService.SearchResultListDto searchPostingsByTitle(@RequestParam(name = "title") String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "제목이 비어있습니다.");
        }
        return postingService.searchByTitle(title);
    }

    // 해시태그로 포스팅들 조회
    @GetMapping("/search/hashtag")
    public PostingService.SearchResultListDto searchPostingByHashtag(@RequestParam(name = "hashtag") String hashtag) {
        if (hashtag == null || hashtag.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해시태그가 비어있습니다.");
        }
        return postingService.searchByHashtag(hashtag);
    }

}


