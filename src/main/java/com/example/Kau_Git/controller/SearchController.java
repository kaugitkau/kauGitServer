package com.example.Kau_Git.controller;

import com.example.Kau_Git.repository.PostingRepository;
import com.example.Kau_Git.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final PostingService postingService;

    @GetMapping("/search/{title}")
    public PostingService.SearchResultListDto searchPostingsByTitle(@PathVariable("title") String title){
        PostingService.SearchResultListDto searchResultDto = postingService.searchByTitle(title);
        return searchResultDto;
    }
}


