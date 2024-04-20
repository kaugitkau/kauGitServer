package com.example.Kau_Git.Oauth;

import com.example.Kau_Git.entity.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    // SessionUser는 인증된 사용자 정보만 필요하므로 아래 필드만 선언
    private String name;
    private String email;
    private Long userId;

    public SessionUser(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();

    }
}