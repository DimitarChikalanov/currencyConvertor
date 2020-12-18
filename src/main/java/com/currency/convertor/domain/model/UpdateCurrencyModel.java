package com.currency.convertor.domain.model;

import java.math.BigDecimal;

public class UpdateCurrencyModel {

    private String currencyName;

    private BigDecimal rate;

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
