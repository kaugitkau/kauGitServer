package com.example.Kau_Git.dto;

import com.example.Kau_Git.dto.pheed.PheedResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class MyPageDto {
    @Builder
    @Getter
    public static class MyPageMyInfo {
        private String name;
        private String introduction;
        private Integer userPoint;
        private String profilePhotoUrl;


        private Double mentorScore;
        private Double sharingScore;
        private Integer numCommunity;
        private Integer numRecommended;
        private Integer numComment;
        private Integer numPheed;




    }

    @Builder
    @Getter
    public static class ListPheedDto{
        List<PheedDto> pheedDtoList;


    }
    @Builder
    @Getter
    public static class PheedDto{
        private Long postingId;
        private String description;
        private List<String> fileUrls;
        private List<String> hashtags;

    }
}
