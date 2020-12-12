package com.currency.convertor.service;

import com.currency.convertor.domain.entity.CurrencyToDay;
import com.currency.convertor.domain.model.CurrencyRequestModel;
import com.currency.convertor.domain.model.ResponseCurrencyModel;
import com.currency.convertor.repository.CurrencyApiClient;
import com.currency.convertor.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
<<<<<<< HEAD
=======
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
>>>>>>> 908d69203c81c9e00dfdbd82cb6c9147aea765a0
import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyApiClient currencyApiClient;

    public CurrencyService(CurrencyRepository currencyRepository, CurrencyApiClient currencyApiClient) {
        this.currencyRepository = currencyRepository;
        this.currencyApiClient = currencyApiClient;
    }

    public BigDecimal convert(CurrencyRequestModel model) {
        CurrencyToDay currencyFrom = this.currencyRepository.findByNameOfValue(model.getExchangeFrom());
        CurrencyToDay currencyTo = this.currencyRepository.findByNameOfValue(model.getExchangeTo());
        BigDecimal exchangeResult = (currencyTo.getRate().divide(currencyFrom.getRate(), 8, RoundingMode.HALF_UP)
                .multiply(model.getSumExchange()));

        return exchangeResult;
    }

    public List getCurrencyRate() {
        return this.currencyRepository.findAll();
    }

    @PostConstruct
    public void save() {
        ResponseCurrencyModel receive = this.currencyApiClient.getLatest();
        receive.getRates().forEach((key, value) -> {
            CurrencyToDay currencyToDay = new CurrencyToDay();
            currencyToDay.setNameOfValue(key);
            currencyToDay.setRate(value);
            currencyToDay.setRefreshTime(receive.getDate());
            this.currencyRepository.saveAndFlush(currencyToDay);
        });
    }

}
