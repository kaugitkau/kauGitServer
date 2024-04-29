package com.example.Kau_Git.dto.pheed;

import lombok.Getter;

public class PheedRequestDto {

    @Getter
    public static class MakePostingDto{
        private String content;
        private String hashtag;
    }
}
