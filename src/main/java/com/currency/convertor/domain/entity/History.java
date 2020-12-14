package com.currency.convertor.domain.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class History extends BaseEntity {

    private String currencyFrom;

    private String currencyTo;

    private BigDecimal currencyExchange;

    private BigDecimal ExchangeSum;

    private LocalDateTime exchangedAt;

    public  History () {
        this.exchangedAt=LocalDateTime.now();
    }

    public String GetCurrencyFrom() {return currencyFrom; }

    public void setCurrencyFrom(String currencyFrom) { this.currencyFrom = currencyFrom; }

    public String GetCurrencyTo() {return currencyTo; }

    public void setCurrencyTo(String currencyTo) { this.currencyTo = currencyTo; }

    public BigDecimal GetCurrencyExchange() { return currencyExchange; }

    public void GetExchangeSum(BigDecimal ExchangeSum) {
        this.ExchangeSum = ExchangeSum;
    }

    public BigDecimal SetExchangeSum() { return ExchangeSum; }

    public void SetCurrencyExchange(BigDecimal currencyExchange) {
        this.currencyExchange = currencyExchange;
    }

    public LocalDateTime GetExchangedAt() {
        return exchangedAt;
    }

    public void SetExchangedAt(LocalDateTime exchangedAt) { this.exchangedAt = exchangedAt; }
}
