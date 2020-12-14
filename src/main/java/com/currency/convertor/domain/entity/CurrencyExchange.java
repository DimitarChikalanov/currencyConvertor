package com.currency.convertor.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "currency_to_dayes")
public class CurrencyExchange extends BaseEntity{

    private String nameOfValue;

    private BigDecimal rate;

    private LocalDate refreshTime;

    public String getNameOfValue() {
        return nameOfValue;
    }

    public void setNameOfValue(String nameOfValue) {
        this.nameOfValue = nameOfValue;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public LocalDate getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(LocalDate refreshTime) {
        this.refreshTime = refreshTime;
    }
}
