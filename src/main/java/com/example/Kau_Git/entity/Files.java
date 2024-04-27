package com.example.Kau_Git.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileUrl;

    private String fileName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "posting_id")//엔티티이름_id여야함. post_id였었음.
    private Posting posting;

    public void createPosting(Posting posting) {
        this.posting = posting;
    }
}
