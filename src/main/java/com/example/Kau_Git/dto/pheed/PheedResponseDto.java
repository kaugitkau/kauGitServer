package com.example.Kau_Git.dto.pheed;

import com.example.Kau_Git.entity.Hashtag;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.List;

public class PheedResponseDto {

    @Builder
    @Getter
    public static class ListPheedDto{
        List<PheedDto> pheedDtoList;


    }
    @Builder
    @Getter
    public static class PheedDto{
        private Long postingId;
        private String writer; //작성자
        private LocalDateTime createdAt; //작성 일자
        private String content; //내용
        private Integer recommendedCnt; //추천 수 - 좋아요 수
        private Integer commentCnt; //해당 게시물에 달린 댓글 수
        private List<String> fileUrls; //파일 링크 - 사진등
        private List<String> hashtags; //해쉬태그


    }
}
