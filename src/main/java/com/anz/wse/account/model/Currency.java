package com.anz.wse.account.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Currency {
    AUD("AUD"), SGD("SGD"), NZD("NZD"), USD("USD"), JPY("JPY"), CAD("CAD");

    private final String currency;
}
