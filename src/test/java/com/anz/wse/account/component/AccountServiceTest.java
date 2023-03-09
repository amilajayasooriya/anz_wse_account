package com.anz.wse.account.component;

import com.anz.wse.account.dto.AccountDTO;
import com.anz.wse.account.model.Account;
import com.anz.wse.account.model.Currency;
import com.anz.wse.account.repository.AccountRepository;
import com.anz.wse.account.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void get_accounts_success() {
        Page<Account> accountPage = getAccountPage();
        when(accountRepository.findByUserId(anyInt(), any())).thenReturn(accountPage);

        Page<AccountDTO> accountDTOPage = accountService.getAccounts(1, Pageable.ofSize(10));

        Assertions.assertFalse(accountPage.isEmpty());
        Assertions.assertEquals(1, accountPage.getSize());

        AccountDTO accountDTO = accountDTOPage.getContent().get(0);
        Account account = accountPage.getContent().get(0);
        Assertions.assertEquals(account.getAccountNumber(), accountDTO.getAccountNumber());
        Assertions.assertEquals(account.getAccountType(), accountDTO.getAccountType());
        Assertions.assertEquals(account.getBalance(), accountDTO.getBalance());
    }

    private static Page<Account> getAccountPage() {
        Account account = Account.builder().
                id(1).
                accountName("SG Savings").
                accountNumber("548795663").
                accountType("Savings").
                balanceDate(new Date()).
                currency(Currency.SGD).
                balance(BigDecimal.valueOf(69845.35)).build();

        return new PageImpl<>(Collections.singletonList(account));
    }
}
