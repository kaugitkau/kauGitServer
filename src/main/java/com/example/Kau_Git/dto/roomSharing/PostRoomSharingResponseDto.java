package com.example.Kau_Git.dto.roomSharing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class PostRoomSharingResponseDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class PostResultDto {
        private String title;
        private String content;
        private String writer;

        private Integer viewCnt;
        private Integer recommendedCnt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class AllPostDto {
        private List<SimplePostDto> list;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class SimplePostDto{
        private Long postId;
        private String title;
    }

}
