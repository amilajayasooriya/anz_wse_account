package com.anz.wse.account.repository.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionType {
    CREDIT("CREDIT"), DEBIT("CREDIT");
    private final String transactionType;
}
