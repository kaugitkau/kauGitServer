package com.example.Kau_Git.controller;

import com.example.Kau_Git.dto.ReportRequestDto;
import com.example.Kau_Git.service.Report.CheckReportService;
import com.example.Kau_Git.service.Report.HandleReportService;
import com.example.Kau_Git.service.Report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    private final CheckReportService checkReportService;
    private final HandleReportService handleReportService;

    @PostMapping("/report")
    public void createReport(@RequestBody ReportRequestDto form) {
        //신고 요청
        reportService.reportContent(form);
    }

    //전체 신고 내용 보기
    //추후 관리자만 볼 수 있게 Session User를 통해 수정 필요
    @GetMapping("/report/monitor/{contentType}")
    public CheckReportService.ReportSearchResultListDto reviewReportList(@PathVariable String contentType) {
        //해당 신고 기록들이 들어있는 res
        CheckReportService.ReportSearchResultListDto res;
        //관리자가 볼 컨텐츠 형식에 맞게 반환
        if (contentType.equalsIgnoreCase("comment")) {
            res =  checkReportService.getAllCommentReport();
        }
        else if (contentType.equalsIgnoreCase("post")) {
            res = checkReportService.getAllPostingReport();
        }
        else{
            throw new IllegalArgumentException("Invalid content type: " + contentType);
        }
        //검색 결과를 반환
        return res;
    }

    //게시물 차단 처리
    @PostMapping("/report/request")
    public void processReport(@RequestBody HandleReportService.BannedContentDto form) {
        handleReportService.bannedContent(form);
    }


}
