package com.currency.convertor.service;

import com.currency.convertor.domain.entity.CurrencyExchange;
import com.currency.convertor.domain.model.CurrencyRequestModel;
import com.currency.convertor.domain.model.ResponseCurrencyModel;
import com.currency.convertor.repository.CurrencyApiClient;
import com.currency.convertor.repository.CurrencyRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

import java.math.RoundingMode;
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
        CurrencyExchange currencyFrom = this.currencyRepository.findByNameOfValue(model.getExchangeFrom());
        CurrencyExchange currencyTo = this.currencyRepository.findByNameOfValue(model.getExchangeTo());
        double exchangeResult = (currencyTo.getRate().doubleValue() / (currencyFrom.getRate().doubleValue()) * (model.getSumExchange().doubleValue()));

        return new BigDecimal(exchangeResult).setScale(2, RoundingMode.HALF_UP);
    }

    public List getCurrencyRate() {
        return this.currencyRepository.findAll();
    }

    @PostConstruct
    @Scheduled(cron = "0 0 17 * * ?")
    public void save() {
        ResponseCurrencyModel receive = this.currencyApiClient.getLatest();
        receive.getRates().forEach((key, value) -> {
            CurrencyExchange currencyExchange = new CurrencyExchange();
            currencyExchange.setNameOfValue(key);
            currencyExchange.setRate(value);
            currencyExchange.setRefreshTime(receive.getDate());
            this.currencyRepository.deleteAll();
            this.currencyRepository.saveAndFlush(currencyExchange);
        });
    }

}
