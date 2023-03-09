package com.anz.wse.account.dto;

import com.anz.wse.account.model.Currency;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.Date;

import static com.anz.wse.account.validation.RegexHelper.ACCOUNT_NUMBER;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDTO extends RepresentationModel<AccountDTO> {

    private int id;

    @NotNull
    @Pattern(regexp = ACCOUNT_NUMBER)
    private String accountNumber;

    @NotNull
    private String accountName;

    @NotNull
    private String accountType;

    @NotNull
    private Date balanceDate;

    @NotNull
    private Currency currency;

    @NotNull
    private BigDecimal balance;
}
