package com.example.Kau_Git.apiPayload.exception;

import com.example.Kau_Git.apiPayload.code.BaseErrorCode;
import com.example.Kau_Git.apiPayload.code.ReasonErrorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode code;

    public ReasonErrorDto getErrorReason() {
        return this.code.getReason();
    }

    public ReasonErrorDto getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}
