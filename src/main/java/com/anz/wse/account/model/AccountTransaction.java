package com.anz.wse.account.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class AccountTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String accountNumber;
    private Date valueDate;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private BigDecimal debitAmount;
    private BigDecimal creditAmount;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private String transactionNarrative;
}
