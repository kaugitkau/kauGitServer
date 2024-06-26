package com.example.Kau_Git.entity;

import com.example.Kau_Git.entity.common.BaseEntity;

import com.example.Kau_Git.entity.enums.MyRole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "USER")
@NoArgsConstructor
@Getter
@Setter
public class User  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    private String id;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "NICKNAME", length = 100)
    private String nickname;

    @Column(name = "PROFILE_IMAGE", length = 1000)
    private String profileImage;

    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @Column(length = 100)
    private String password;

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
    private Integer userPoint=0;

    private String introduction;//한줄소개

    @Column(name = "HAS_BANNED_FLAG", nullable = false)
    private Boolean hasBannedFlag = false;

    private Double mentoringAvgRated;

    private Double sharingAvgRated;

    private String language;
    @Enumerated(EnumType.STRING) // Enum값을 어떤 형태로 저장할지 결정합니다. (기본적은 int)
    @Column(nullable = false)
    private MyRole role; // 사용자의 권한을 관리할 Enum 클래스

    @Builder
    public User(String id, String name, String email, MyRole role, Integer gender, Date birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public User update(String name) {
        this.name = name;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public long getUserId(){
        return userId;
    }

    public void setUserBanStatus(boolean status){
        this.hasBannedFlag = status;
    }

    public int getPoint() {
        return userPoint;
    }
}