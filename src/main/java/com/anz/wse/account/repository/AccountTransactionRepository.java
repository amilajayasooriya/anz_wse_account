package com.anz.wse.account.repository;

import com.anz.wse.account.repository.entity.AccountTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {
    Page<AccountTransaction> findByAccountUserIdAndAccountAccountNumber(int userId, String accountNumber, Pageable pageable);
}
