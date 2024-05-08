package com.example.Kau_Git.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostingProfileDto {
    private String nickname;
    private String photo;
    private String religion;

    private String language;
    private String motto;

    private Double avgRated;
    private String region;
}
