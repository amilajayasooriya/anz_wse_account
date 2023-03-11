package component.com.anz.wse.account.repository;

import com.anz.wse.account.model.Account;
import com.anz.wse.account.repository.AccountRepository;
import component.com.anz.wse.account.RepositoryTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@RepositoryTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void get_accounts_tw0_account_for_user() {
       Page<Account> accountOptional = accountRepository.findByUserId(100, Pageable.ofSize(10));
       Assertions.assertEquals(2, accountOptional.getContent().size());
    }

    @Test
    public void get_accounts_no_account_for_user() {
        Page<Account> accountOptional = accountRepository.findByUserId(300, Pageable.ofSize(10));
        Assertions.assertEquals(0, accountOptional.getContent().size());
    }

    @Test
    public void get_account_success() {
        Optional<Account> accountOptional = accountRepository.findByUserIdAndAccountNumber(100, "100309209");
        Assertions.assertTrue(accountOptional.isPresent());

        Assertions.assertEquals("100309209", accountOptional.get().getAccountNumber());
    }

    @Test
    public void get_account_userid_account_number_not_matching() {
        Optional<Account> accountOptional = accountRepository.findByUserIdAndAccountNumber(100, "200066619");
        Assertions.assertTrue(accountOptional.isEmpty());
    }

    @Test
    public void get_account_userid_invalid_account_number_invalid() {
        Optional<Account> accountOptional = accountRepository.findByUserIdAndAccountNumber(56, "45343233");
        Assertions.assertTrue(accountOptional.isEmpty());
    }
}
