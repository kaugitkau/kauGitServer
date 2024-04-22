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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long commentId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "POST_ID", referencedColumnName = "POST_ID", foreignKey = @ForeignKey(name = "FK_COMMENT_POST_ID"))
    private Posting posting;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "WRITER_ID", referencedColumnName = "USERID", foreignKey = @ForeignKey(name = "FK_COMMENT_WRITER_ID"))
    private User writer;

    private Integer ord;

    @Column(name = "CONTENT", length = 300)
    private String content;

    @Column(name = "WARNING_CNT")
    private Integer warningCnt;

    @Column(name = "IS_HIDE_FLAG", nullable = false)
    private boolean isHideFlag = false;
}