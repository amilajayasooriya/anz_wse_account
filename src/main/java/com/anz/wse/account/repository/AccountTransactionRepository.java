package com.anz.wse.account.repository;

import com.anz.wse.account.model.AccountTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {
    Page<AccountTransaction> findByAccountAccountNumber(String accountNumber, Pageable pageable);
}
