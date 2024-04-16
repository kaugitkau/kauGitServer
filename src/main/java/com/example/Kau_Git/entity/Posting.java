package com.example.Kau_Git.entity;

import com.example.Kau_Git.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "POSTING")
@Builder
@AllArgsConstructor
public class Posting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long postId;

    @Column(name = "TYPE", length = 1)
    private char type;

    @Column(name = "POST_ORD")
    private Short postOrd;

//    @Column(name = "WRITE_DATE", nullable = false)
//    private LocalDateTime writeDate = LocalDateTime.now();

    @Column(name = "CLASSIFICATION", length = 1)
    private char classification;

    @Column(name = "CONTENT", length = 4000)
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "WRITER_ID", referencedColumnName = "USERID", foreignKey = @ForeignKey(name = "FK_WRITER_ID"))
    private User writer;

    @Column(name = "HASHTAG", length = 1000)
    private String hashtag;

    @Column(name = "REPORT_CNT")
    private Short reportCnt;

    @Column(name = "VIEW_CNT")
    private Integer viewCnt;

    @Column(name = "RECOMMENT_CNT")
    private Integer recommentCnt;

    @Column(name = "IS_HIDE", nullable = false)
    private boolean isHide = false;
}
