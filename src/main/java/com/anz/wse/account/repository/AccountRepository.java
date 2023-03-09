package com.anz.wse.account.repository;

import com.anz.wse.account.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserIdAndAccountNumber(int userId, String accountNumber);

    Page<Account> findByUserId(int userId, Pageable pageable);
}
