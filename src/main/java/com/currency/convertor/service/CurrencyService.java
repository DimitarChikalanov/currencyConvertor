package com.currency.convertor.service;

import com.currency.convertor.domain.entity.CurrencyToDay;
import com.currency.convertor.domain.model.CurrencyRequestModel;
import com.currency.convertor.domain.model.ResponseCurrencyModel;
import com.currency.convertor.repository.CurrencyApiClient;
import com.currency.convertor.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    private final CurrencyApiClient currencyApiClient;


    public CurrencyService(CurrencyRepository currencyRepository, CurrencyApiClient currencyApiClient) {
        this.currencyRepository = currencyRepository;
        this.currencyApiClient = currencyApiClient;
    }


    public String convert(CurrencyRequestModel model) {
        ResponseCurrencyModel receive = this.currencyApiClient.getLatest();
        for (Map.Entry<String, BigDecimal> entry : receive.getRates().entrySet()) {
            CurrencyToDay currencyToDay = new CurrencyToDay();
            currencyToDay.setNameOfValue(entry.getKey());
            currencyToDay.setRate(entry.getValue());
            currencyToDay.setRefreshTime(receive.getDate());
            this.currencyRepository.saveAndFlush(currencyToDay);
        }


        CurrencyToDay currencyFrom = this.currencyRepository.findByNameOfValue(model.getExchangeFrom());
        CurrencyToDay currencyTo = this.currencyRepository.findByNameOfValue(model.getExchangeTo());

        return "ee";
    }

    private void saveToDb() {

    }

//    private ResponseCurrencyModel receiveData() {
//        return
//    }


}
