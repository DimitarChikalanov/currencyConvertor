package com.currency.convertor.domain.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

    @ManyToOne
    private User user ;

    public  History () {
        this.exchangedAt=LocalDateTime.now();
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
        return ExchangeSum;
    }

    public void setExchangeSum(BigDecimal exchangeSum) {
        ExchangeSum = exchangeSum;
    }

    public LocalDateTime getExchangedAt() {
        return exchangedAt;
    }

    public void setExchangedAt(LocalDateTime exchangedAt) {
        this.exchangedAt = exchangedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
