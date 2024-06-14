package com.example.Kau_Git.dto.report;

import lombok.Builder;
import lombok.Getter;

public class ReportPostingDto {
    @Getter
    @Builder
    public static class MakeReportPostingDto{
        //게시물Id
        private Long postingId;
        //신고자가 설정한 카테고리
        private String category;
        //신고자가 작성한 세부내용
        private String reason;
    }
}
