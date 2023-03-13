package com.anz.wse.account.service;

import com.anz.wse.account.dto.AccountDTO;
import com.anz.wse.account.exception.ResourceNotFoundException;
import com.anz.wse.account.repository.entity.Account;
import com.anz.wse.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public Page<AccountDTO> getAccounts(int userId, Pageable pageable) {
        log.debug("message=\"Get account list request received\"");
        Page<Account> accountPage = accountRepository.findByUserId(userId, pageable);
        if (accountPage.isEmpty()) {
            log.info("message=\"No account found for the user\"");
            throw new ResourceNotFoundException("Account not found for the user");
        }

        return accountPage.map(account -> modelMapper.map(account, AccountDTO.class));
    }
}
