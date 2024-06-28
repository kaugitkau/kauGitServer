package com.example.Kau_Git.entity;

import com.example.Kau_Git.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "COMMENT")
@Builder
@AllArgsConstructor
public class Comment extends BaseEntity {

    //Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long commentId;

    //게시물(댓글을 작성한)의 ID
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "POSTING_ID")
    private Posting posting;

    //댓글 작성자 ID
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User writer;

    //댓글 내의 순서 -> 작성 날짜로 설정(이것을 기준으로 정렬)
    private Integer ord;

    //댓글 내용
    @Column(name = "CONTENT", length = 300)
    private String content;

    //신고 당한 횟수 (다른 사용자가 신고를 몇 회 하였는지)
    @Column(name = "WARNING_CNT")
    private Integer warningCnt;

    //숨김 여부 - 가이드라인 위반으로 Hide처리 되었는지
    @Column(name = "IS_HIDE_FLAG", nullable = false)
    private boolean isHideFlag = false;

    //신고횟수 증가
    public void incrementWarningCnt(){this.warningCnt += 1;}
    //신고횟수 감소
    public void decrementWarningCnt(){this.warningCnt -= 1;}
    //게시물 보이기 & 숨기기
    public void changeHideState(Boolean setValue){this.isHideFlag = setValue;}


//    @PreRemove
//    private void preRemove() {
//        if (posting != null) {
//            this.posting = null;
//        }
//    }
}