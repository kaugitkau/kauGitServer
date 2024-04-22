package com.example.Kau_Git.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

public class CommentResponseDto {
    @Getter
    @Builder
    public static class CommentPreviewDto{
        private String writerId;
        private LocalDateTime createdDate;
        private String content;
        private String nickName;
    }

    @Getter
    @Builder
    public static class CommentListDto {
        List<CommentPreviewDto> dtoList;

    }
}
