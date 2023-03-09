package com.anz.wse.account.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.anz.wse.account.validation.RegexHelper.ACCOUNT_NUMBER;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private int userId;

    @Column(unique=true)
    @NotNull
    @Pattern(regexp = ACCOUNT_NUMBER)
    private String accountNumber;

    @NotNull
    private String accountName;

    @NotNull
    private String accountType;

    @NotNull
    private Date balanceDate;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Currency currency;

    @NotNull
    private BigDecimal balance;

    @OneToMany
    private List<AccountTransaction> accountTransactions;
}
