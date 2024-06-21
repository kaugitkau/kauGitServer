package com.example.Kau_Git.apiPayload.code;

public interface BaseCode { //이 interface의 역할은, 이를 구체화 하는 Status에서 두 개의 메소드를 반드시 Override할 것을 강제하는 역할

    public ReasonDto getReason();

    public ReasonDto getReasonHttpStatus();
}