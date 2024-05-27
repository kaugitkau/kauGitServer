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

<<<<<<< HEAD
    //댓글 등록
=======
    //댓글을 작성하는 경우
>>>>>>> b90a956 (주석 처리)
    @PostMapping("/comment/{postId}")
    public void addComment(@PathVariable Long postingId, @RequestBody CommentRequestDto.AddCommentDto addCommentDto,
                           @Login SessionUser sessionUser) {
        Long userId = sessionUser.getUserId();

        commandService.addComment(postingId, userId, addCommentDto);
    }

<<<<<<< HEAD
    //댓글 목록 조회
=======
    //
>>>>>>> b90a956 (주석 처리)
    @GetMapping("/comment/{postId}")
    public List<CommentResponseDto.CommentPreviewDto> getCommentPreviewList(@PathVariable Long postId) {
        List<CommentResponseDto.CommentPreviewDto> commentPreviewDtos = commandService.showComments(postId);
        return commentPreviewDtos;
    }

<<<<<<< HEAD
    //댓글 삭제
=======
    //댓글을 삭제하는 경우
>>>>>>> b90a956 (주석 처리)
    @DeleteMapping("/comment/{commentId}")
    public void deleteComment(@PathVariable Long commentId,
                              @Login SessionUser sessionUser) {
        Long userId = sessionUser.getUserId();
        commandService.deleteComment(userId, commentId);
    }
}
