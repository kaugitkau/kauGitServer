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
        private String title;
        private String description;
        private String region;
        private LocalDateTime createdAt;
        private Integer commentCount;
        private Integer recommendedCount;

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListDto {
        List<PreviewDto> previewDtoList;
    }
}
