package com.anz.wse.account.service;

import com.anz.wse.account.dto.AccountTransactionDTO;
import com.anz.wse.account.model.AccountTransaction;
import com.anz.wse.account.repository.AccountTransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountTransactionService {

    private final AccountTransactionRepository accountTransactionRepository;
    private final ModelMapper modelMapper;

    public Page<AccountTransactionDTO> getAccountTransaction(String accountNumber, Pageable pageable) {
        log.debug("message=\"Get account transaction request received\"");
        Page<AccountTransaction> accountTransactionDTOOptional = accountTransactionRepository.findByAccountAccountNumber(accountNumber, pageable);
        if (accountTransactionDTOOptional.isEmpty()) {
            log.info("message=\"No account transactions found for the account number\"");
        }

        return accountTransactionDTOOptional.map(accountTransaction -> modelMapper.map(accountTransaction, AccountTransactionDTO.class));
    }
}
