package com.example.Kau_Git.apiPayload.code.status;

import com.example.Kau_Git.apiPayload.code.BaseCode;
import com.example.Kau_Git.apiPayload.code.ReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class SuccessStatus implements BaseCode{

    // 일반적인 응답
//    _OK(HttpStatus.OK, "COMMON200", "성공입니다.");

    // 멤버 관련 응답

    // ~~~ 관련 응답

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDto getReason() {
        return ReasonDto.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDto getReasonHttpStatus() {
        return ReasonDto.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build()
                ;
    }

}
