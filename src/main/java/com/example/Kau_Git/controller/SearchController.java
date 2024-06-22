package com.example.Kau_Git.controller;

import com.example.Kau_Git.service.PostingService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final PostingService postingService;

    //제목으로 포스팅 조회
    @GetMapping("/search/title")
    public PostingService.SearchResultListDto searchPostingsByTitle(@RequestParam(name = "title") String title){
        PostingService.SearchResultListDto searchResultDto = postingService.searchByTitle(title);
        return searchResultDto;
    }


    //해쉬태그로 포스팅들 조회
    @GetMapping("/search/hashtag")
    public PostingService.SearchResultListDto searchPostingByHashtag(@RequestParam(name = "hashtag") String hashtag){
        PostingService.SearchResultListDto searchResultListDto = postingService.searchByHashtag(hashtag);
        return searchResultListDto;
    }

}


