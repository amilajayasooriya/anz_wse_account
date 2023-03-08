package com.anz.wse.account.dto;

import com.anz.wse.account.model.Currency;
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
public class AccountDTO {
    private int id;
    private String accountNumber;
    private String accountName;
    private String accountType;
    private Date balanceDate;
    private Currency currency;
    private BigDecimal balance;
}
