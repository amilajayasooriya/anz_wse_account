package com.anz.wse.account.controller;

import com.anz.wse.account.dto.AccountDTO;
import com.anz.wse.account.service.AccountService;
import com.anz.wse.account.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AuthService authService;

    @GetMapping("/v1/account")
    public ResponseEntity<Page<AccountDTO>> getAccounts(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "20") int size,
                                                        @RequestParam(defaultValue = "id") String sortBy,
                                                        @RequestHeader("x-authToken") String authToken,
                                                        @RequestHeader("x-correlationId") String correlationId) {

     /*   int userId;
        try {
            userId = authService.getUserIdFromAuthToken(authToken);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
*/
        log.debug("message=\"Get account request received\"");
        return ResponseEntity.of(Optional.ofNullable(accountService.getAccounts(PageRequest.of(page, size, Sort.by(sortBy)))));
    }
}
