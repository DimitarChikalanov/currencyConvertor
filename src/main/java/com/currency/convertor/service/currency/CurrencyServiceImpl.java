package com.currency.convertor.service.currency;

import com.currency.convertor.domain.entity.CurrencyExchange;
import com.currency.convertor.domain.entity.History;
import com.currency.convertor.domain.entity.User;
import com.currency.convertor.domain.model.CurrencyRequestModel;
import com.currency.convertor.domain.model.ResponseCurrencyModel;
import com.currency.convertor.repository.CurrencyApiClient;
import com.currency.convertor.repository.CurrencyRepository;
import com.currency.convertor.repository.HistoryRepository;
import com.currency.convertor.repository.UserRepository;
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
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, CurrencyApiClient currencyApiClient, HistoryRepository historyRepository, UserRepository userRepository) {
        this.currencyRepository = currencyRepository;
        this.currencyApiClient = currencyApiClient;
        this.historyRepository = historyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BigDecimal convert(CurrencyRequestModel model, User user) {
        CurrencyExchange currencyFrom = this.currencyRepository.findByNameOfValue(model.getExchangeFrom());
        CurrencyExchange currencyTo = this.currencyRepository.findByNameOfValue(model.getExchangeTo());
        double exchangeResult = (currencyTo.getRate().doubleValue() / (currencyFrom.getRate().doubleValue()) * (model.getSumExchange().doubleValue()));
//        User us = this.userRepository.findByUsername(user.getUsername()).orElseThrow();

        if (user.getId() != 0) {
            History history = new History();
            history.setUser(user);
            history.setCurrencyFrom(model.getExchangeFrom());
            history.setCurrencyTo(model.getExchangeTo());
            history.setCurrencyExchange(model.getSumExchange());
            history.setExchangeSum(new BigDecimal(exchangeResult).setScale(2, RoundingMode.HALF_UP));
            this.historyRepository.saveAndFlush(history);
        }

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
        this.currencyRepository.deleteAll();
        receive.getRates().forEach((key, value) -> {
            CurrencyExchange currencyExchange = new CurrencyExchange();
            currencyExchange.setNameOfValue(key);
            currencyExchange.setRate(value);
            currencyExchange.setRefreshTime(receive.getDate());
            this.currencyRepository.saveAndFlush(currencyExchange);
        });
    }

}
