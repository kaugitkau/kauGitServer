package com.example.Kau_Git.service.Report;

import com.example.Kau_Git.entity.Comment;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.repository.CommentRepository;
import com.example.Kau_Git.repository.PostingRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckReportService {
    private final PostingRepository postingRepository;
    private final CommentRepository commentRepository;

    //전체 댓글에 대한 신고 목록을 가져오기
    public ReportSearchResultListDto getAllCommentReport (){
        //신고 횟수가 0보다 큰 것을 모두 가져옴
        List<Comment> allCommentReport = commentRepository.findAllWithValueGreaterThan(0);
        //신고 결과를 저장할 리스트
        List<ReportSearchResultDto> searchResultDtoList = new ArrayList<>();

        for(Comment c : allCommentReport){
            //신고된 댓글 중 id, 내용 요약, 신고횟수만 저장
            ReportSearchResultDto build = ReportSearchResultDto.builder()
                    .contentId(c.getCommentId())
                    .summary(makeSummary(c.getContent()))
                    .banCount(c.getWarningCnt())
                    .build();
            //필요한 내용만 만들어 리스트에 저장
            searchResultDtoList.add(build);
        }
        //DTO로 만듬
        ReportSearchResultListDto list = ReportSearchResultListDto.builder()
                .searchResultDtoList(searchResultDtoList)
                .build();

        return list;
    }

    //전체 게시물에 대한 신고 목록을 가져오기
    public ReportSearchResultListDto getAllPostingReport (){
        //신고 횟수가 0보다 큰 것을 모두 가져옴
        List<Posting> allPostingReport = postingRepository.findAllWithValueGreaterThan(0);
        //필요한 내용만 선별해서 저장
        List<ReportSearchResultDto> searchResultDtoList = new ArrayList<>();

        for(Posting p : allPostingReport){
            ReportSearchResultDto build = ReportSearchResultDto.builder()
                    .contentId(p.getPostingId())
                    .summary(makeSummary(p.getContent()))
                    .banCount(p.getReportCnt())
                    .build();

            searchResultDtoList.add(build);
        }

        ReportSearchResultListDto list = ReportSearchResultListDto.builder()
                .searchResultDtoList(searchResultDtoList)
                .build();

        return list;
    }


    public String makeSummary(String content){
        if(content.length() > 20){
            return content.substring(0, 20);
        }
        else return content;
    }


    @Getter
    @Builder
    public static class ReportSearchResultDto{
        Long contentId;
        String summary;
        Integer banCount;
    }

    @Getter
    @Builder
    public static class ReportSearchResultListDto{
        List<ReportSearchResultDto> searchResultDtoList;
    }

}


