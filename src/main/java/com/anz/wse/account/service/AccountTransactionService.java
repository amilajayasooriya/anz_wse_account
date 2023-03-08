package com.anz.wse.account.service;

import com.anz.wse.account.dto.AccountTransactionDTO;
import com.anz.wse.account.repository.AccountTransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountTransactionService {

    private final AccountTransactionRepository accountTransactionRepositoryRepository;
    private final ModelMapper modelMapper;

    public List<AccountTransactionDTO> getAccounts(){
        return accountTransactionRepositoryRepository.findAll().stream().map(account -> modelMapper.map(account, AccountTransactionDTO.class)).toList();
    }
}
