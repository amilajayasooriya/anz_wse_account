package com.anz.wse.account.repository;

import com.anz.wse.account.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Optional<Account> findByUserIdAndAccountNumber(int userId, String accountNumber);

    Page<Account> findByUserId(int userId, Pageable pageable);
}
