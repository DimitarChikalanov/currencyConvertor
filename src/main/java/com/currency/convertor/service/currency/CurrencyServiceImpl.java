package com.currency.convertor.service.currency;

import com.currency.convertor.domain.entity.CurrencyExchange;
import com.currency.convertor.domain.entity.User;
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
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyApiClient currencyApiClient;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, CurrencyApiClient currencyApiClient) {
        this.currencyRepository = currencyRepository;
        this.currencyApiClient = currencyApiClient;
    }

    @Override
    public BigDecimal convert(CurrencyRequestModel model, User user) {
        CurrencyExchange currencyFrom = this.currencyRepository.findByNameOfValue(model.getExchangeFrom());
        CurrencyExchange currencyTo = this.currencyRepository.findByNameOfValue(model.getExchangeTo());
        double exchangeResult = (currencyTo.getRate().doubleValue() / (currencyFrom.getRate().doubleValue()) * (model.getSumExchange().doubleValue()));

        return new BigDecimal(exchangeResult).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public List getCurrencyRate() {
        return this.currencyRepository.findAll();
    }

    @PostConstruct
    @Scheduled(cron = "0 0 05 * * ?")
    public void save() {
        ResponseCurrencyModel receive = this.currencyApiClient.getLatest();
        receive.getRates().forEach((key, value) -> {
            CurrencyExchange currencyExchange = new CurrencyExchange();
            this.currencyRepository.delete(currencyExchange);
            currencyExchange.setNameOfValue(key);
            currencyExchange.setRate(value);
            currencyExchange.setRefreshTime(receive.getDate());
            this.currencyRepository.saveAndFlush(currencyExchange);
        });
    }

}
