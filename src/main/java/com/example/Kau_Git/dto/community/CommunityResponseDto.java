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
        private Long postingId;
        private Integer viewCnt;
        private Integer recommendedCnt;
        private LocalDateTime createdAt;
        private String region;
        private String title;
        private String content;
        private List<String> hashTag;

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PreviewDto {
        private Long postingId;
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
