package com.example.Kau_Git.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PheedHashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FEEDHASHTAG_ID")
    private Long id;

    public PheedHashtag(Posting posting, Hashtag hashtag) {

    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "posting_id")
    private Posting posting;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;
}
