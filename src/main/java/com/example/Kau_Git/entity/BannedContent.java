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
    @Column(name = "BANNED_ID")
    private Long bannedId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USERID", referencedColumnName = "USERID", foreignKey = @ForeignKey(name = "FK_BANNEDCONTENT_USER_ID"))
    private User user;

    @Column(name = "REASON", length = 1000)
    private String reason;
}