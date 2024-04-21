package com.example.Kau_Git.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name ="FILES")
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 100)
    private String id;

    @Column(name = "FileUrl", length = 300)
    private String fileUrl;

    @Column(name = "FileName", length = 100)
    private String filename;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Posting posting;
}
