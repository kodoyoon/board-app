package com.sparta.boardapp.dto.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
public class ErrorResponseDto{

    private final Error error;

    public ErrorResponseDto(int status, String msg) {
        this.error = new Error(status, msg);
    }

    @JsonPropertyOrder({"status", "message"})
    record Error(
            int status,
            @JsonProperty("message") //반환할때는 메시지로 하고 싶다.
            String msg
    ){}
}
