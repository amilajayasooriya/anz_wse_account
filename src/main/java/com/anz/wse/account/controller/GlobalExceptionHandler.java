package com.anz.wse.account.controller;

import com.anz.wse.account.dto.ApiError;
import com.anz.wse.account.exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.UUID;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

private static final String CONSTRAINT_ERROR_CODE = "CONER";
private static final String ILLEGAL_ERROR_CODE = "ILER";
private static final String BAD_REQUEST_ERROR_CODE = "BDER";
private static final String NOT_FOUND_ERROR_CODE = "NTER";
private static final String RUNTIME_ERROR_CODE = "RTER";

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException e) {
        String errorId = UUID.randomUUID().toString();
        log.error("ConstraintViolationException exception occurred. Error id: {}", errorId, e);
        ApiError apiError = ApiError.builder().
                errorId(errorId).
                errorCode(CONSTRAINT_ERROR_CODE).
                message(e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList().toString()).
                timestamp(Instant.now()).
        build();

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException e) {
        String errorId = UUID.randomUUID().toString();
        log.error("IllegalArgumentException exception occurred. Error id: {}", errorId, e);
        ApiError apiError = ApiError.builder().
                errorId(errorId).
                errorCode(ILLEGAL_ERROR_CODE).
                message(e.getMessage()).
                timestamp(Instant.now()).
                build();

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ApiError> handleHttpClientErrorBadRequestException(HttpClientErrorException.BadRequest e) {
        String errorId = UUID.randomUUID().toString();
        log.error("HttpClientErrorException.BadRequest exception occurred. Error id: {}", errorId, e);
        ApiError apiError = ApiError.builder().
                errorId(errorId).
                errorCode(BAD_REQUEST_ERROR_CODE).
                message(e.getMessage()).
                timestamp(Instant.now()).
                build();

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException e) {
        String errorId = UUID.randomUUID().toString();
        log.error("HttpClientErrorException.NotFound exception occurred. Error id: {}", errorId, e);
        ApiError apiError = ApiError.builder().
                errorId(errorId).
                errorCode(NOT_FOUND_ERROR_CODE).
                message(e.getMessage()).
                timestamp(Instant.now()).
                build();

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private ResponseEntity<ApiError> handleServerException(RuntimeException e) {
        String errorId = UUID.randomUUID().toString();
        log.error("RuntimeException exception occurred. Error id: {}", errorId, e);
        ApiError apiError = ApiError.builder().
                errorId(errorId).
                errorCode(RUNTIME_ERROR_CODE).
                message(e.getMessage()).
                timestamp(Instant.now()).
                build();

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
