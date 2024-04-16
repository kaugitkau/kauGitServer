package com.example.Kau_Git.dto.roomSharing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class PostRoomSharingRequestDto {

    @Getter
    @Builder
    public static class RegistPostDto{

        private String title;
        private String content;
        private LocalDate startDate;
        private LocalDate endDate;
    }
}
