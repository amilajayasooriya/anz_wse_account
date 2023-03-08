package com.anz.wse.account.dto;

import com.anz.wse.account.model.Currency;
import com.anz.wse.account.model.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountTransactionDTO {
    private int id;
    private String accountNumber;
    private Date valueDate;
    private Currency currency;
    private BigDecimal debitAmount;
    private BigDecimal creditAmount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private String transactionNarrative;
}
