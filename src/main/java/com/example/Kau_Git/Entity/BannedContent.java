package com.example.Kau_Git.Entity;

import jakarta.persistence.*;
@Entity
@Table(name = "BANNEDCONTENT")
public class BannedContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Character classification;

    @Column(length = 1000)
    private String reason;

}