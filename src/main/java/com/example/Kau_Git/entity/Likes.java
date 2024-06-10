package com.example.Kau_Git.entity;

import com.example.Kau_Git.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "Likes")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Likes extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKES_ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "POSTING_ID")
    private Posting posting;

    @PreRemove
    private void preRemove() {
        if (posting != null) {
            this.posting = null;
        }
    }
}
