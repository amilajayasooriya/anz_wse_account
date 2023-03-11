package unit.com.anz.wse.account.service;

import com.anz.wse.account.dto.AccountDTO;
import com.anz.wse.account.model.Account;
import com.anz.wse.account.model.Currency;
import com.anz.wse.account.repository.AccountRepository;
import com.anz.wse.account.service.AccountService;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void get_accounts_list_success() {
        AccountDTO mockAccountDTO = getAccountDTO();
        when(accountRepository.findByUserId(anyInt(), any())).thenReturn(getAccountPage());
        when(modelMapper.map(any(Account.class), any())).thenReturn(mockAccountDTO);

        Page<AccountDTO> accountFromServicePage = accountService.getAccounts(1, Pageable.ofSize(10));

        Assertions.assertFalse(accountFromServicePage.isEmpty());
        Assertions.assertEquals(1, accountFromServicePage.getSize());
        Assertions.assertEquals(mockAccountDTO, accountFromServicePage.getContent().get(0));
    }

    @Test
    public void get_accounts_list_no_results() {
        when(accountRepository.findByUserId(anyInt(), any())).thenReturn(getAccountPage());
        when(modelMapper.map(any(Account.class), any())).thenReturn(null);

        Page<AccountDTO> accountFromServicePage = accountService.getAccounts(2, Pageable.ofSize(10));
        Assertions.assertFalse(accountFromServicePage.isEmpty());
        Assertions.assertNull(accountFromServicePage.getContent().get(0));
    }

    @Test
    public void get_accounts_one_account_success() {
        AccountDTO mockAccountDTO = getAccountDTO();
        when(accountRepository.findByUserIdAndAccountNumber(anyInt(), anyString())).thenReturn(getAccountOptional());
        when(modelMapper.map(any(Account.class), any())).thenReturn(mockAccountDTO);

        Optional<AccountDTO> accountDTOOptional = accountService.getAccount(1, "234643444");
        Assertions.assertTrue(accountDTOOptional.isPresent());
        Assertions.assertEquals(accountDTOOptional.get(), mockAccountDTO);
    }

    @Test
    public void get_accounts_one_account_invalid_userid_or_account_number() {
        when(accountRepository.findByUserIdAndAccountNumber(anyInt(), anyString())).thenReturn(Optional.empty());

        Optional<AccountDTO> accountDTOOptional = accountService.getAccount(1, "234643444");
        Assertions.assertTrue(accountDTOOptional.isEmpty());
    }

    public Optional<Account> getAccountOptional() {
        return Optional.of(Account.builder().build());
    }

    private Page<Account> getAccountPage() {
        Account account = Account.builder().
                build();

        return new PageImpl<>(Collections.singletonList(account));
    }

    private AccountDTO getAccountDTO() {
        return AccountDTO.builder().
                id(1).
                accountName("SG Savings").
                accountNumber("548795663").
                accountType("Savings").
                balanceDate(new Date()).
                currency(Currency.SGD).
                balance(BigDecimal.valueOf(69845.35)).build();
    }
}
