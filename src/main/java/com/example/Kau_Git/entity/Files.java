package com.example.Kau_Git.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileUrl;

    private String filename;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "posting_id")//엔티티이름_id여야함. post_id였었음.
    private Posting posting;
}
