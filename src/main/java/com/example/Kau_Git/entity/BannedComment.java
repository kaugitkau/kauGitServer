package com.example.Kau_Git.entity;

import com.example.Kau_Git.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "BANNEDCOMMENT")
public class BannedComment extends BaseEntity {
    //정지 처리된 댓글 사이에 구분을 위한 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BANNEDPCOMMENT_ID")
    private Long bannedCommentId;

    //정지처리된 카테고리
    @Column(name = "BannedCategory")
    private String bannedCategory;

    //정지 처리된 댓글 작성자
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USERID")
    private User user;

    //정지 처리된 댓글
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COMMENT_ID")
    private Comment comment;

    //정지 처리된 세부 이유
    @Column(name = "REASON", length = 1000)
    private String reason;
}