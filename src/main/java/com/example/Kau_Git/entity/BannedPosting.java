package com.example.Kau_Git.entity;

import com.example.Kau_Git.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "BANNEDPOSTING")
public class BannedPosting extends BaseEntity {
    //정지된 게시물을 식별하기 위한 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BANNEDPOSTING_ID")
    private Long bannedPostingId;

    //정지 처리된 게시물 Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="POSTING_ID")
    private Posting posting;

    //정지 처리된 사유
    @Column(name = "BannedCategory")
    private String bannedCategory;

    //정지 처리된 게시물 작성자의 Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USERID")
    private User user;

    //정지 사유
    @Column(name = "REASON", length = 1000)
    private String reason;
}