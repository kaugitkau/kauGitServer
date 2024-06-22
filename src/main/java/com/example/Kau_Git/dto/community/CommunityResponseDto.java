package com.example.Kau_Git.dto.community;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class CommunityResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostingDto {
        private Integer viewCnt; //추천수
        private Integer recommendedCnt; //좋아요 수
        private LocalDateTime createdAt; //생성날짜
        private String region; //지역
        private String title; //제목
        private String content; //내용
        private String hashTag; //게시물의 HashTag

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PreviewDto {
        private Long postingId;
        private String title; //제목
        private String description; //한 줄 요약
        private String region; //지역
        private LocalDateTime createdAt; //작성 날짜
        private Integer commentCount; //댓글 수
        private Integer recommendedCount; //추천(좋아요) 수

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListDto {
        List<PreviewDto> previewDtoList; //preview들의 목록(게시판에서 글 목록들)
    }
}
