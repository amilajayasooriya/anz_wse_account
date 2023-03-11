package com.anz.wse.account.repository;

import com.anz.wse.account.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void get_accounts_list() {
       Optional<Account> accountOptional = accountRepository.findByUserIdAndAccountNumber(1, "585309209");
       Assertions.assertTrue(accountOptional.isPresent());
    }
}
