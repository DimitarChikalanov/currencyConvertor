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
    public BigDecimal convertAndSaveToHistory(CurrencyRequestModel model, User user) {
        History history = new History();
        history.setUser(user);
        history.setCurrencyFrom(model.getExchangeFrom());
        history.setCurrencyTo(model.getExchangeTo());
        history.setCurrencyExchange(model.getSumExchange());
        history.setExchangeSum(sumOfCurrency(model.getExchangeTo(), model.getExchangeFrom(), model.getSumExchange()));
        this.historyRepository.saveAndFlush(history);

        return sumOfCurrency(model.getExchangeTo(), model.getExchangeFrom(), model.getSumExchange());
    }

    @Override
    public List getCurrencyRate() {
        return this.currencyRepository.findAll();
    }

    @Override
    public BigDecimal convertCurrency(CurrencyRequestModel model) {
        return sumOfCurrency(model.getExchangeTo(), model.getExchangeFrom(), model.getSumExchange());
    }

    private BigDecimal sumOfCurrency(String rateTo, String rateFrom, BigDecimal sum) {
        CurrencyExchange currencyFrom = this.currencyRepository.findByNameOfValue(rateFrom);
        CurrencyExchange currencyTo = this.currencyRepository.findByNameOfValue(rateTo);
        double exchangeResult = (currencyTo.getRate().doubleValue() / (currencyFrom.getRate().doubleValue()) * (sum.doubleValue()));
        return new BigDecimal(exchangeResult).setScale(2, RoundingMode.HALF_UP);
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
