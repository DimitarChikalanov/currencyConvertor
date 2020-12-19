package com.currency.convertor.domain.model;

import java.math.BigDecimal;

public class CurrencyRequestModel {

    private String exchangeFrom;

    private String exchangeTo;

    private BigDecimal sumExchange;

    public CurrencyRequestModel() { }

    public String getExchangeFrom() {
        return exchangeFrom;
    }

    public void setExchangeFrom(String exchangeFrom) {
        this.exchangeFrom = exchangeFrom;
    }

    public String getExchangeTo() {
        return exchangeTo;
    }

    public void setExchangeTo(String exchangeTo) {
        this.exchangeTo = exchangeTo;
    }

    public BigDecimal getSumExchange() {
        return sumExchange;
    }

    public void setSumExchange(BigDecimal sumExchange) {
        this.sumExchange = sumExchange;
    }
}
