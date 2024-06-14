package com.example.Kau_Git.dto.report;

import lombok.Builder;
import lombok.Getter;

public class ReportCommentDto {
    @Getter
    @Builder
    public static class MakeReportCommentDto{
        //댓글 Id
        private Long commentId;
        //신고자가 설정한 Id
        private String category;
        //신고자가 작성한 세부내용
        private String reason;
    }
}
