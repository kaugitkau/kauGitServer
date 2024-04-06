package com.example.Kau_Git.Entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "COMMENT")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private Long postId;

    private Integer writerId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date writeDate;

    private Short ord;

    private Short groupOrd;

    @Column(length = 300)
    private String content;

    private Integer warningCnt;

    @Column(nullable = false)
    private Boolean isHideFlag = false;

}