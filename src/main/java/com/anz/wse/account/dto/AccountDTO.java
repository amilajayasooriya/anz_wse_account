package com.anz.wse.account.dto;

import com.anz.wse.account.model.Currency;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class AccountDTO extends RepresentationModel<AccountDTO> {

    @NotNull
    @JsonIgnore
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AccountDTO that = (AccountDTO) o;

        if (id != that.id) return false;
        if (!accountNumber.equals(that.accountNumber)) return false;
        if (!accountName.equals(that.accountName)) return false;
        if (!accountType.equals(that.accountType)) return false;
        if (!balanceDate.equals(that.balanceDate)) return false;
        if (currency != that.currency) return false;
        return balance.equals(that.balance);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id;
        result = 31 * result + accountNumber.hashCode();
        result = 31 * result + accountName.hashCode();
        result = 31 * result + accountType.hashCode();
        result = 31 * result + balanceDate.hashCode();
        result = 31 * result + currency.hashCode();
        result = 31 * result + balance.hashCode();
        return result;
    }
}
