package com.anz.wse.account.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionType {
    CREDIT("CREDIT"), DEBIT("CREDIT");
    private final String transactionType;
}
