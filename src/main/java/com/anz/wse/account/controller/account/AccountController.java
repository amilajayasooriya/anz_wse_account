package com.anz.wse.account.controller.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
