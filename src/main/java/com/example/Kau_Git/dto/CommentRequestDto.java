package com.example.Kau_Git.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class CommentRequestDto {
    @Getter
    @Builder
    public static class AddCommentDto{
        private String content;

    }




}
