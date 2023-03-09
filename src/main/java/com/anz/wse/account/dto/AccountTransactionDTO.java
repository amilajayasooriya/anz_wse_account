package com.anz.wse.account.dto;

import com.anz.wse.account.model.Currency;
import com.anz.wse.account.model.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class AccountTransactionDTO extends RepresentationModel<AccountTransactionDTO> {

    @NotNull
    private int id;

    @NotNull
    @Pattern(regexp = ACCOUNT_NUMBER)
    private String accountNumber;

    @NotNull
    private Date valueDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Currency currency;

    private BigDecimal debitAmount;

    private BigDecimal creditAmount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String transactionNarrative;
}
