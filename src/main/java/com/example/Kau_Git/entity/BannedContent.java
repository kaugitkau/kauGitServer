package com.example.Kau_Git.entity;

import com.example.Kau_Git.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "BANNEDCONTENT")
public class BannedContent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BANNEDCONTENT_ID")
    private Long bannedContentId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USERID")
    private User user;

    @Column(name = "REASON", length = 1000)
    private String reason;
}