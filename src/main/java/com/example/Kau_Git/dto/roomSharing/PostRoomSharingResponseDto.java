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
        private String title; //제목
        private String content; //내용
        private String writer; //작성자

        private Integer viewCnt; //조회수
        private Integer recommendedCnt; //추천(좋아요) 수
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
