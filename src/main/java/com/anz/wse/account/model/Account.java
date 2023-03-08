package com.anz.wse.account.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String accountNumber;
    private String accountType;
    private Date balanceDate;
    private Currency Currency;
    private BigDecimal balance;
}
