package com.example.Kau_Git.dto.roomSharing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
public class PostRoomSharingRequestDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegistPostDto{

        private String title;
        private String content;
        private LocalDate startDate;
        private LocalDate endDate;
    }
}
