package com.currency.convertor.service;

import com.currency.convertor.domain.entity.CurrencyToDay;
import com.currency.convertor.domain.model.CurrencyRequestModel;
import com.currency.convertor.domain.model.ResponseCurrencyModel;
import com.currency.convertor.repository.CurrencyApiClient;
import com.currency.convertor.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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
        double result = model.getSumExchange().doubleValue() * (currencyFrom.getRate().doubleValue() * currencyTo.getRate().doubleValue());
        return BigDecimal.valueOf(result);
    }

    public Map<String, BigDecimal> getCurrencyRate(String currency) {
        CurrencyToDay currencyToDay = this.currencyRepository.findByNameOfValue(currency);
        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        map.putIfAbsent(currencyToDay.getNameOfValue(), currencyToDay.getRate());
        return map;
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
