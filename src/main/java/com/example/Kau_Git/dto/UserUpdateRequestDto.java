package com.example.Kau_Git.dto;


import lombok.Data;

@Data
public class UserUpdateRequestDto {
    private String address;
    private String religion;
    private String nationality;
    private int gender;
    private String nickname;
}
