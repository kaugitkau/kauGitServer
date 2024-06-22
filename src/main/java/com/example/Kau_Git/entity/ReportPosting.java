package com.example.Kau_Git.entity;

import com.example.Kau_Git.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportPosting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="REPORTPOSTING_ID")
    private long reportPostingId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="POSTING_ID")
    private Posting posting;

    @Column(name="reportCommentCategory")
    private String category;

    @Column(name="reportCommentReason")
    private String reason;
}
