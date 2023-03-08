package com.anz.wse.account.controller;

import com.anz.wse.account.dto.AccountDTO;
import com.anz.wse.account.dto.AccountTransactionDTO;
import com.anz.wse.account.service.AccountService;
import com.anz.wse.account.service.AccountTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountTransactionController {

    private final AccountTransactionService accountTransactionService;
    @GetMapping("/v1/account-transaction/{accountNumber}")
    public ResponseEntity<List<AccountTransactionDTO>> getAccountTransaction(@PathVariable final String accountNumber) {
        return ResponseEntity.of(Optional.ofNullable(accountTransactionService.getAccounts()));
    }
}
