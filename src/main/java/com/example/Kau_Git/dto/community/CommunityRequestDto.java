package com.example.Kau_Git.dto.community;

import lombok.Getter;

import java.time.LocalDateTime;

public class CommunityRequestDto {

    @Getter
    public static class AddPostingDto{
        private String title;
        private String content;
        private String hashTag;


    }


}
