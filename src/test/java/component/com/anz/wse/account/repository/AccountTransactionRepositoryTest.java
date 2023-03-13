package component.com.anz.wse.account.repository;

import com.anz.wse.account.repository.entity.AccountTransaction;
import com.anz.wse.account.repository.AccountTransactionRepository;
import component.com.anz.wse.account.RepositoryTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RepositoryTest
public class AccountTransactionRepositoryTest {

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    @Test
    public void findByAccountUserIdAndAccountAccountNumber_withValidInput_returnSuccess() {
        Page<AccountTransaction> accountTransactionPage = accountTransactionRepository.findByAccountUserIdAndAccountAccountNumber(100, "100309209", Pageable.ofSize(10));
        Assertions.assertEquals(3, accountTransactionPage.getContent().size());
        accountTransactionPage.forEach(accountTransaction -> Assertions.assertEquals("100309209", accountTransaction.getAccount().getAccountNumber()));
    }

    @Test
    public void findByAccountUserIdAndAccountAccountNumber_withUserIdAccountNumberMismatched_returnEmptyPage() {
        Page<AccountTransaction> accountTransactionPage = accountTransactionRepository.findByAccountUserIdAndAccountAccountNumber(100, "200066619", Pageable.ofSize(10));
        Assertions.assertEquals(0, accountTransactionPage.getContent().size());
    }

    @Test
    public void findByAccountUserIdAndAccountAccountNumber_withInvalidUserIdAndValidAccountNumber_returnEmptyPage() {
        Page<AccountTransaction> accountTransactionPage = accountTransactionRepository.findByAccountUserIdAndAccountAccountNumber(54, "200066619", Pageable.ofSize(10));
        Assertions.assertEquals(0, accountTransactionPage.getContent().size());
    }

    @Test
    public void findByAccountUserIdAndAccountAccountNumber_withInvalidUserIdInvalidAccountNumber_returnEmptyPage() {
        Page<AccountTransaction> accountTransactionPage = accountTransactionRepository.findByAccountUserIdAndAccountAccountNumber(54, "840066619", Pageable.ofSize(10));
        Assertions.assertEquals(0, accountTransactionPage.getContent().size());
    }
}
