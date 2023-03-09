package com.anz.wse.account.service;

import com.anz.wse.account.dto.AccountDTO;
import com.anz.wse.account.model.Account;
import com.anz.wse.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public Optional<AccountDTO> getAccount(int userId, String accountNumber) {
        log.debug("message=\"Get account request received\"");

        Optional<Account> accountOptional = accountRepository.findByUserIdAndAccountNumber(userId, accountNumber);
        return accountOptional.map(account -> modelMapper.map(account, AccountDTO.class));
    }

    public Page<AccountDTO> getAccounts(int userId, Pageable pageable) {
        log.debug("message=\"Get account list request received\"");
        return accountRepository.findByUserId(userId, pageable).map(account -> modelMapper.map(account, AccountDTO.class));
    }
}
