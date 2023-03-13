package com.anz.wse.account.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
@Getter
public class ApiError {
    private String errorId;
    private String errorCode;
    private String message;

    private Instant timestamp;
}