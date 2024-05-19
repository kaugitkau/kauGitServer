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
        private String writerId; //작성자 ID
        private LocalDateTime createdDate; //작성 날짜
        private String content; //댓글 내용
        private String nickName; //작성자의 별명(회원가입시 입력받는)
    }

    @Getter
    @Builder
    public static class CommentListDto {
        List<CommentPreviewDto> dtoList;

    }
}
