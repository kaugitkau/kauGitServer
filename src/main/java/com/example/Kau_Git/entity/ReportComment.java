package com.example.Kau_Git.entity;

import com.example.Kau_Git.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ValueGenerationType;

@Entity
@Builder
@Table(name="REPORTCOMMMENT")
@AllArgsConstructor
@NoArgsConstructor
public class ReportComment extends BaseEntity {

    //식별자 - 식별을 위해 별도로 Id를 부여
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPORTCOMMENT_ID")
    private Long reportCommId;

    //신고 대상인 댓글을 가리키는 속성
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="COMMENT_ID")
    private Comment comment;

    //신고 카테고리
    @Column(name="reportCommentCategory")
    private String category;

    //신고 세부내용
    @Column(name="reportCommentReason")
    private String reason;


}
