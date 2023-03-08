package com.anz.wse.account.controller;

import com.anz.wse.account.dto.AccountDTO;
import com.anz.wse.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    @GetMapping("/v1/account")
    public ResponseEntity<List<AccountDTO>> getAccounts() {
        return ResponseEntity.of(Optional.ofNullable(accountService.getAccounts()));
    }
}
