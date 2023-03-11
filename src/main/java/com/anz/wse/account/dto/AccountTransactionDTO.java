package com.anz.wse.account.dto;

import com.anz.wse.account.model.Currency;
import com.anz.wse.account.model.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.Date;

import static com.anz.wse.account.validation.RegexHelper.ACCOUNT_NUMBER;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AccountTransactionDTO that = (AccountTransactionDTO) o;

        if (id != that.id) return false;
        if (!accountNumber.equals(that.accountNumber)) return false;
        if (!valueDate.equals(that.valueDate)) return false;
        if (currency != that.currency) return false;
        if (!debitAmount.equals(that.debitAmount)) return false;
        if (!creditAmount.equals(that.creditAmount)) return false;
        if (transactionType != that.transactionType) return false;
        return transactionNarrative.equals(that.transactionNarrative);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id;
        result = 31 * result + accountNumber.hashCode();
        result = 31 * result + valueDate.hashCode();
        result = 31 * result + currency.hashCode();
        result = 31 * result + debitAmount.hashCode();
        result = 31 * result + creditAmount.hashCode();
        result = 31 * result + transactionType.hashCode();
        result = 31 * result + transactionNarrative.hashCode();
        return result;
    }
}
