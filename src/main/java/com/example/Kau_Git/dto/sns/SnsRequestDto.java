package com.example.Kau_Git.dto.sns;

import lombok.Getter;

public class SnsRequestDto {

    @Getter
    public static class MakePostingDto{
        private String threeLine;
        private String hashtag;
    }
}
