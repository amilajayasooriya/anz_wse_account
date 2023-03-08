package com.anz.wse.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionType {
    CREDIT("Credit"), DEBIT("Debit");
    private String transactionType;
}
