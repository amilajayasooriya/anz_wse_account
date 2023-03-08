package com.anz.wse.account.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AccountController {
    @GetMapping("/v1/account")
    public String getAccounts() {
        return "Greetings from Spring Boot!";
    }
}
