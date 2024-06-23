package com.example.Kau_Git.service.Report;

import com.example.Kau_Git.entity.*;
import com.example.Kau_Git.repository.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HandleReportService {
    private static final Logger log = LoggerFactory.getLogger(HandleReportService.class);
    private final UserRepository userRepository;

    private final PostingRepository postingRepository;
    private final CommentRepository commentRepository;

    private final ReportCommentRepository reportCommentRepository;
    private final ReportPostingRepository reportPostingRepository;

    private final BannedCommentRepository bannedCommentRepository;
    private final BannedPostingRepository bannedPostingRepository;

    @Transactional
    public void bannedContent(BannedContentDto content){
        if (content.contentType.equals("comment")) {
            bannedComment(content);
        }
        else if(content.contentType.equals("posting")){
            bannedPosting(content);
        }
        else{
            log.error("정의되지 않은 형식에 대한 신고 처리 요청");
        }
        //removeAllReport(content.category, content.contentId);
    }

    private void bannedComment(BannedContentDto content ){
        //ID로 댓글을 찾아옴
        Comment comment = commentRepository.findByCommentId(content.contentId);

        //댓글을 숨김 처리
        comment.changeHideState(true);

        //DB에 저장하기 전에 Entity 생성
        BannedComment build = BannedComment.builder()
                .bannedCategory(content.category)
                .user(comment.getWriter())
                .comment(comment)
                .reason(content.reason)
                .build();
        //DB에 저장
        bannedCommentRepository.save(build);
        //작성자에 대해서 차단 여부 설정
        setBannedUser(comment.getWriter(), content.isBanUser);
    }

    private void bannedPosting(BannedContentDto content){
        //ID로 댓글을 찾아옴
        Posting post = postingRepository.findByPostingId(content.contentId);
        //게시물을 숨김 처리
        post.changeHideState(true);
        //DB에 저장하기 위해 Entity 생성
        BannedPosting build = BannedPosting.builder()
                .posting(postingRepository.findByPostingId(content.contentId))
                .bannedCategory(content.category)
                .user(post.getWriter())
                .reason(content.reason)
                .build();
        //DB에 저장
        bannedPostingRepository.save(build);

        //작성자에 대해서 차단을 설정
        setBannedUser(post.getWriter(), content.isBanUser);
    }

    private void setBannedUser(User target, Boolean setUserType){
        //추후, 차단 당한 사용자를 풀어야 될 경우, if문을 삭제하는 걸로 진행
        if(setUserType){
            //차단 처리
            target.setUserBanStatus(setUserType);
            //수정 값을 DB에 반영
            userRepository.save(target);
        }
    }

    private void removeAllReport(String contentType, Long contentId){
        if (contentType.equals("comment")) {
            reportCommentRepository
                    .deleteByCommentId(contentId);
        }
        else if(contentType.equals("posting")){
            reportPostingRepository
                    .deleteByPostingId(contentId);
        }
        else{
            log.error("정의되지 않은 형식에 대한 신고 기록 삭제 요청");
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BannedContentDto{
        //게시물의 Id
        Long contentId;
        //게시물의 종류 (게시물인지 댓글인지)
        String contentType;
        //카테고리 - 차단된 카테고리
        String category;
        //세부 이유
        String reason;
        //User 차단
        Boolean isBanUser;
    }



}
