package unit.com.anz.wse.account.service;

import com.anz.wse.account.dto.AccountDTO;
import com.anz.wse.account.exception.ResourceNotFoundException;
import com.anz.wse.account.repository.entity.Account;
import com.anz.wse.account.repository.entity.Currency;
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

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void getAccounts_withValidInput_returnSuccess() {
        AccountDTO mockAccountDTO = getAccountDTO();
        when(accountRepository.findByUserId(anyInt(), any())).thenReturn(getAccountPage());
        when(modelMapper.map(any(Account.class), any())).thenReturn(mockAccountDTO);

        Page<AccountDTO> accountFromServicePage = accountService.getAccounts(1, Pageable.ofSize(10));

        Assertions.assertFalse(accountFromServicePage.isEmpty());
        Assertions.assertEquals(1, accountFromServicePage.getSize());
        Assertions.assertEquals(mockAccountDTO, accountFromServicePage.getContent().get(0));
    }

    @Test
    public void getAccounts_withInvalidInput_throwsResourceNotFoundException() {
        when(accountRepository.findByUserId(anyInt(), any())).thenReturn(Page.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> accountService.getAccounts(67, Pageable.ofSize(10)));
    }

    @Test
    public void getAccounts_withRuntimeExceptionInRepository_throwsRuntimeException() {
        when(accountRepository.findByUserId(anyInt(), any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> accountService.getAccounts(67, Pageable.ofSize(10)));
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
