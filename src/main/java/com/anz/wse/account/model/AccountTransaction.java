package com.anz.wse.account.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @NotNull
    private Account account;

    @NotNull
    private Date valueDate;

    private BigDecimal debitAmount;

    private BigDecimal creditAmount;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TransactionType transactionType;

    private String transactionNarrative;
}
