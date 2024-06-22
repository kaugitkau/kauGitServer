package com.example.Kau_Git.service.Report;

import com.example.Kau_Git.dto.ReportRequestDto;
import com.example.Kau_Git.dto.report.ReportCommentDto;
import com.example.Kau_Git.dto.report.ReportPostingDto;
import com.example.Kau_Git.entity.Comment;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.entity.ReportComment;
import com.example.Kau_Git.entity.ReportPosting;
import com.example.Kau_Git.repository.CommentRepository;
import com.example.Kau_Git.repository.PostingRepository;
import com.example.Kau_Git.repository.ReportCommentRepository;
import com.example.Kau_Git.repository.ReportPostingRepository;
import com.example.Kau_Git.service.AbstractPostingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportService extends AbstractPostingService {
    private static final Logger log = LoggerFactory.getLogger(ReportService.class);
    private final PostingRepository postingRepository;
    private final CommentRepository commentRepository;

    private final ReportCommentRepository reportCommentRepository;
    private final ReportPostingRepository reportPostingRepository;

    @Transactional
    public void reportContent(ReportRequestDto reportRequestDto) {
        String type = reportRequestDto.getContentType();

        if(type.equals("posting")){
            reportPosting(reportRequestDto);
        }
        else if(type.equals("comment")){
            reportComment(reportRequestDto);
        }
        else{
            log.error("존재하지 않는 content 종류에 대한 신고");
        }
    }

    //댓글에 대한 신고 요청이 발생한 경우, DB에 저장하는 API
    private void reportComment(ReportRequestDto reportCommentDto){
        //ID로 댓글을 찾아옴
        Comment comment = commentRepository.findByCommentId(reportCommentDto.getContentId());

        //DB에 저장할 Entity 생성
        ReportComment build = ReportComment.builder()
                .comment(comment) //댓글
                .category(reportCommentDto.getReportCategory()) //카테고리
                .reason(reportCommentDto.getReportContent())//이유
                .build(); //Entity 생성

        //Entity를 DB에 저장
        reportCommentRepository.save(build);

        //기준 : 몇 개의 신고가 접수되면, 관리자 처리 전에 숨김처리를 할지
        int guideValue = 100;

        //신고 횟수를 하나 추가
        comment.incrementWarningCnt();

        //업데이트한 신고 횟수를 가져옴
        int cnt = comment.getWarningCnt();

        //신고 횟수가 기준을 넘은 경우 --> Hide 처리
        //추후 새로운 방안으로 변경 예정
        if(cnt > guideValue){
            comment.changeHideState(false);
        }

        //결과를 반영한 entity 저장
        commentRepository.save(comment);
    }

    private void reportPosting(ReportRequestDto reportPostingDto){
        //ID로 게시물을 찾아옴
        Posting post = postingRepository.findByPostingId(reportPostingDto.getContentId());

        //DB에 저장할 Entity 생성
        ReportPosting build = ReportPosting.builder()
                .posting(post)
                .category(reportPostingDto.getReportCategory())
                .reason(reportPostingDto.getReportContent())
                .build();


        //Entity를 DB에 저장
        reportPostingRepository.save(build);

        //기준 : 몇 개의 신고가 접수되면, 관리자 처리 전에 숨김처리를 할지
        int guideValue = 100;

        //신고 횟수를 하나 추가
        post.incrementReportCnt();

        //업데이트한 신고 횟수를 가져옴
        int cnt = post.getReportCnt();

        //신고 횟수가 기준을 넘은 경우 --> Hide 처리
        //추후 새로운 방안으로 변경 예정
        if(cnt > guideValue){
            post.changeHideState(false);
        }

        //결과를 반영한 entity 저장
        postingRepository.save(post);
    }
}
