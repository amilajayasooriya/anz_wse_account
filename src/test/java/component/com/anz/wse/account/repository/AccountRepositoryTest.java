package component.com.anz.wse.account.repository;

import com.anz.wse.account.model.Account;
import com.anz.wse.account.repository.AccountRepository;
import component.com.anz.wse.account.RepositoryTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RepositoryTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void get_accounts_tow_account_for_user() {
       Page<Account> accountOptional = accountRepository.findByUserId(100, Pageable.ofSize(10));
       Assertions.assertEquals(2, accountOptional.getContent().size());
    }

    @Test
    public void get_accounts_no_account_for_user() {
        Page<Account> accountOptional = accountRepository.findByUserId(300, Pageable.ofSize(10));
        Assertions.assertEquals(0, accountOptional.getContent().size());
    }
}
