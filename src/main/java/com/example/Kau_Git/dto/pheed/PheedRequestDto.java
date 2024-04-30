package com.example.Kau_Git.dto.pheed;

import lombok.Getter;

import java.util.List;

public class PheedRequestDto {

    @Getter
    public static class MakePostingDto{
        private String content;
        private List<String> hashtags;
    }
}
