package com.example.Kau_Git.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PostingHashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FEEDHASHTAG_ID")
    private Long id;

    public PostingHashtag(Posting posting, Hashtag hashtag) {
        this.hashtag = hashtag;
        this.posting = posting;

    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "posting_id")
    private Posting posting;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;
}
