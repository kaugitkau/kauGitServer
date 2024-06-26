package com.example.Kau_Git.controller;

import com.example.Kau_Git.Oauth.Login;
import com.example.Kau_Git.Oauth.SessionUser;
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

    //댓글 등록
    @PostMapping("/comment/{postingId}")
    public void addComment(@PathVariable(name = "postingId") Long postingId, @RequestBody CommentRequestDto.AddCommentDto addCommentDto,
                           @Login SessionUser sessionUser) {
        Long userId = sessionUser.getUserId();

        commandService.addComment(postingId, userId, addCommentDto);
    }

    //댓글 목록 조회
    @GetMapping("/comment/{postId}")
    public List<CommentResponseDto.CommentPreviewDto> getCommentPreviewList(@PathVariable Long postId) {
        List<CommentResponseDto.CommentPreviewDto> commentPreviewDtos = queryService.showComments(postId);
        return commentPreviewDtos;
    }

    //댓글 삭제 - 제약조건 때문에 안된다.
    @DeleteMapping("/comment/{commentId}")
    public void deleteComment(@PathVariable Long commentId,
                              @Login SessionUser sessionUser) {
        Long userId = sessionUser.getUserId();

        commandService.deleteComment(userId, commentId);
    }
}
