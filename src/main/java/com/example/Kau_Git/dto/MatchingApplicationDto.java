package com.example.Kau_Git.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class MatchingApplicationDto {
    private Long applicantId;  // 'id'를 대문자 'Id'로 수정
    private Long guideId;
}