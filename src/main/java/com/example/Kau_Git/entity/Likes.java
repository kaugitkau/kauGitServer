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

    //LIKES_ID(Key) - Table내에서 구별을 위한 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKES_ID")
    private Long id;

    //좋아요를 누른 사용자의 ID
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    //좋아요가 눌러진 게시물의 ID
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
