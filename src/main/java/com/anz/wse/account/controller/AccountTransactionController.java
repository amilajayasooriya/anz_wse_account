package com.anz.wse.account.controller;

import com.anz.wse.account.dto.AccountTransactionDTO;
import com.anz.wse.account.service.AccountTransactionService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.anz.wse.account.validation.RegexHelper.ACCOUNT_NUMBER;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class AccountTransactionController {

    private final AccountTransactionService accountTransactionService;
    @GetMapping("/v1/account-transaction/{accountNumber}")
    public ResponseEntity<Page<AccountTransactionDTO>> getAccountTransaction(@PathVariable @Valid @Pattern(regexp = ACCOUNT_NUMBER, message = "Invalid account number format") final String accountNumber,
                                                                             @RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "20") int size,
                                                                             @RequestParam(defaultValue = "id") String sortBy,
                                                                             @RequestHeader(name = "x-authToken") String authToken,
                                                                             @RequestHeader(name = "x-correlationId") String correlationId) {

        log.debug("message=\"Get account transaction request received\"");
        return ResponseEntity.of(Optional.ofNullable(accountTransactionService.getAccountTransaction(accountNumber, PageRequest.of(page, size, Sort.by(sortBy)))));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<List<String>> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList(), HttpStatus.BAD_REQUEST);
    }
}
