package com.example.Kau_Git.controller;

import com.example.Kau_Git.service.CommentCommandService;
import com.example.Kau_Git.service.CommentQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentQueryService queryService;
    private final CommentCommandService commandService;

    @PostMapping("/comment/{postId}/{userId}")
    public void addComment(@PathVariable Long postId, @PathVariable Long userId) {

    }
}
