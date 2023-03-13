package com.anz.wse.account.controller;

import com.anz.wse.account.dto.AccountDTO;
import com.anz.wse.account.service.AccountService;
import com.anz.wse.account.service.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.anz.wse.account.validation.RegexHelper.ACCOUNT_NUMBER;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class AccountController {

    private final AccountService accountService;
    private final AuthService authService;

    @GetMapping("/v1/accounts")
    public ResponseEntity<Page<AccountDTO>> getAccounts(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "20") int size,
                                                        @RequestParam(defaultValue = "id") String sortBy,
                                                        @RequestHeader("x-authToken") String authToken,
                                                        @RequestHeader("x-correlationId") String correlationId) {
        log.debug("message=\"Get accounts list request received\"");

        int  userId = authService.getUserIdFromAuthToken(authToken);

        Page<AccountDTO> accountDTOPage = accountService.getAccounts(userId, PageRequest.of(page, size, Sort.by(sortBy))).
                map(accountDTO -> {
                    accountDTO.add(linkTo(methodOn(AccountController.class).
                            getAccounts(page, size, sortBy, authToken, correlationId)).withSelfRel());
                    accountDTO.add(linkTo(methodOn(AccountTransactionController.class).
                            getAccountTransaction(accountDTO.getAccountNumber(), page, size, "id", authToken, correlationId)).withRel("account-transaction"));

                    return accountDTO;
                });

        return ResponseEntity.of(Optional.of(accountDTOPage));
    }
}
