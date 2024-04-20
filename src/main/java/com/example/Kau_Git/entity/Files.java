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
    private String id;

    private String fileUrl;
    private String filename;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Posting posting;
}
