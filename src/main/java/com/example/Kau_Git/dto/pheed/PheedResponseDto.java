package com.example.Kau_Git.dto.pheed;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class PheedResponseDto {

    @Builder
    public static class ListPheedDto{
        List<PheedDto> pheedDtoList;


    }
    @Builder
    @Getter
    public static class PheedDto{
        private String content;
    }
}
