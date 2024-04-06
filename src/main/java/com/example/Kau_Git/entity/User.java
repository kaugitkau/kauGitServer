package com.example.Kau_Git.entity;
import com.example.Kau_Git.entity.common.BaseEntity;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "USER")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "USERID", nullable = false)
    private Long userId;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "NICKNAME", length = 100)
    private String nickname;

    @Column(name = "PROFILE_IMAGE", length = 1000)
    private String profileImage;

    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @Column(name = "GENDER")
    private Integer gender;

    @Column(name = "RELIGION", length = 100)
    private String religion;

    @Column(name = "NATIONALITY", length = 100)
    private String nationality;

    @Column(name = "ADDRESS", length = 100)
    private String address;

    @Column(name = "CONTACT_TYPE")
    private Integer contactType;

    @Column(name = "CONTACT_DETAIL", length = 100)
    private String contactDetail;

    @Column(name = "USER_POINT")
    private Integer userPoint;

    @Column(name = "HAS_BANNED_FLAG", nullable = false)
    private Boolean hasBannedFlag = false;


}