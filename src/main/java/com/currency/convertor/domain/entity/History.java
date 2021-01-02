package com.currency.convertor.domain.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class History extends BaseEntity {

    private String currencyFrom;

    private String currencyTo;

    private BigDecimal currencyExchange;

    private BigDecimal sumExchange;

    private LocalDate exchangedAt;

    @ManyToOne
    private User user;

    public History () {
        this.exchangedAt = LocalDate.now();
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public BigDecimal getCurrencyExchange() {
        return currencyExchange;
    }

    public void setCurrencyExchange(BigDecimal currencyExchange) {
        this.currencyExchange = currencyExchange;
    }

    public BigDecimal getExchangeSum() {
        return sumExchange;
    }

    public void setExchangeSum(BigDecimal exchangeSum) {
        sumExchange = exchangeSum;
    }

    public LocalDate getExchangedAt() {
        return exchangedAt;
    }

    public void setExchangedAt(LocalDate exchangedAt) {
        this.exchangedAt = exchangedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
