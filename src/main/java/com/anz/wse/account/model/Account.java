package com.anz.wse.account.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String accountNumber;
    private String accountName;
    private String accountType;
    private Date balanceDate;

    @Enumerated(EnumType.STRING)
    private Currency currency;
    private BigDecimal balance;
}
