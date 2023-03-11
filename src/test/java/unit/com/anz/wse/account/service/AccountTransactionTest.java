package unit.com.anz.wse.account.service;

import com.anz.wse.account.dto.AccountTransactionDTO;
import com.anz.wse.account.model.AccountTransaction;
import com.anz.wse.account.model.Currency;
import com.anz.wse.account.model.TransactionType;
import com.anz.wse.account.repository.AccountTransactionRepository;
import com.anz.wse.account.service.AccountTransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class AccountTransactionTest {

    @InjectMocks
    private AccountTransactionService accountTransactionService;

    @Mock
    private AccountTransactionRepository accountTransactionRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void get_account_transaction_list_success() {
        AccountTransactionDTO mockAccountTransactionDTO = getAccountTransactionDTO();
        when(accountTransactionRepository.findByAccountUserIdAndAccountAccountNumber(anyInt(), anyString(), any())).thenReturn(getAccountTransactionPage());
        when(modelMapper.map(any(AccountTransaction.class), any())).thenReturn(mockAccountTransactionDTO);

        Page<AccountTransactionDTO> accountTransactionFromServicePage = accountTransactionService.
                getAccountTransaction(1, "234789123", Pageable.ofSize(10));

        Assertions.assertFalse(accountTransactionFromServicePage.isEmpty());
        Assertions.assertEquals(1, accountTransactionFromServicePage.getSize());
        Assertions.assertEquals(mockAccountTransactionDTO, accountTransactionFromServicePage.getContent().get(0));
    }

    @Test
    public void get_accounts_transaction_list_no_results() {
        when(accountTransactionRepository.findByAccountUserIdAndAccountAccountNumber(anyInt(), anyString(), any())).thenReturn(getAccountTransactionPage());
        when(modelMapper.map(any(AccountTransaction.class), any())).thenReturn(null);

        Page<AccountTransactionDTO> accountTransactionFromServicePage = accountTransactionService.
                getAccountTransaction(2, "097628372", Pageable.ofSize(10));
        Assertions.assertFalse(accountTransactionFromServicePage.isEmpty());
        Assertions.assertNull(accountTransactionFromServicePage.getContent().get(0));
    }

    private Page<AccountTransaction> getAccountTransactionPage() {
        AccountTransaction accountTransaction = AccountTransaction.builder().
                build();

        return new PageImpl<>(Collections.singletonList(accountTransaction));
    }

    private AccountTransactionDTO getAccountTransactionDTO() {
        return AccountTransactionDTO.builder().
                id(1).
                accountNumber("548795663").
                transactionType(TransactionType.CREDIT).
                creditAmount(BigDecimal.valueOf(5632.25)).
                currency(Currency.SGD).
                valueDate(new Date()).
                build();
    }
}
