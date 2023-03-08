package com.anz.wse.account.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class AccountTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String accountNumber;
    private Date valueDate;
    private Currency currency;
    private BigDecimal debitAmount;
    private BigDecimal creditAmount;
    private TransactionType transactionType;
    private String transactionNarrative;
}
