package com.example.Kau_Git.Entity;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    private String nickname;

    @Column(length = 1000)
    private String profileImage;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    private Integer gender;

    private String religion;

    private String nationality;

    private String address;

    private Integer contactType;

    @Column(name = "contactDetail")
    private String contactDetail;

    private Integer userPoint;

    @Column(nullable = false)
    private Boolean hasBannedFlag = false;

}