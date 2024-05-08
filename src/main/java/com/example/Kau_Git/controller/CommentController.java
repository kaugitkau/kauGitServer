package com.example.Kau_Git.controller;

import com.example.Kau_Git.dto.CommentRequestDto;
import com.example.Kau_Git.dto.CommentResponseDto;
import com.example.Kau_Git.service.CommentCommandService;
import com.example.Kau_Git.service.CommentQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentQueryService queryService;
    private final CommentCommandService commandService;
    private final Long testId = 1L;

    @PostMapping("/comment/{postId}/{userId}")
    public void addComment(@PathVariable Long postId, @PathVariable Long userId, @RequestBody CommentRequestDto.AddCommentDto addCommentDto) {
        commandService.addComment(postId, userId, addCommentDto);
    }

    @GetMapping("/comment/{postId}")
    public List<CommentResponseDto.CommentPreviewDto> getCommentPreviewList(@PathVariable Long postId) {
        List<CommentResponseDto.CommentPreviewDto> commentPreviewDtos = commandService.showComments(postId);
        return commentPreviewDtos;
    }

    @DeleteMapping("/comment/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commandService.deleteComment(testId, commentId);
    }
}
