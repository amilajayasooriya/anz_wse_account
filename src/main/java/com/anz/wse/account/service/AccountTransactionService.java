package com.anz.wse.account.service;

import com.anz.wse.account.dto.AccountTransactionDTO;
import com.anz.wse.account.exception.ResourceNotFoundException;
import com.anz.wse.account.repository.entity.AccountTransaction;
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

    public Page<AccountTransactionDTO> getAccountTransaction(int userId, String accountNumber, Pageable pageable) {
        log.debug("message=\"Get account transaction request received\"");
        Page<AccountTransaction> accountTransactionPage = accountTransactionRepository.
                findByAccountUserIdAndAccountAccountNumber(userId, accountNumber, pageable);
        if (accountTransactionPage.isEmpty()) {
            log.info("message=\"No account transactions found for the account number\"");
            throw new ResourceNotFoundException("Transactions not found for the account");
        }

        return accountTransactionPage.map(accountTransaction -> modelMapper.map(accountTransaction, AccountTransactionDTO.class));
    }
}
