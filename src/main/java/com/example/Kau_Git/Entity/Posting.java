package com.example.Kau_Git.Entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "POSTING")
public class Posting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private Character type;

    private Short postOrd;

    @Temporal(TemporalType.TIMESTAMP)
    private Date writeDate;

    private Character classification;

    @Column(length = 4000)
    private String content;

    private Long writerId;

    @Column(length = 1000)
    private String hashtag;

    private Short reportCnt;

    private Integer viewCnt;

    private Integer recommentCnt;

    @Column(nullable = false)
    private Boolean isHide = false;

    // Getters and setters...
}
