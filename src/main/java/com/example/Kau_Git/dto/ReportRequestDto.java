package com.example.Kau_Git.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequestDto {
        private Long contentId; //신고 대상(게시물, 댓글)의 id
        private String contentType; //신고 대상의 종류
        private String reportCategory; //신고자가 선택한 범주
        private String reportContent; //신고자가 작성한 세부 내용
}
