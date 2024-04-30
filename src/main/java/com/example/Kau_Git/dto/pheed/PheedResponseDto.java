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
        private String writer;
        private LocalDateTime createdAt;
        private String content;
        private Integer recommendedCnt;
        private Integer commentCnt;
        private List<String> fileUrls;
        private List<String> hashtags;


    }
}
