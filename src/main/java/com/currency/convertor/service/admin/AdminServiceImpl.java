package com.currency.convertor.service.admin;

import com.currency.convertor.domain.entity.CurrencyExchange;
import com.currency.convertor.domain.entity.ERole;
import com.currency.convertor.domain.entity.User;
import com.currency.convertor.domain.model.UpdateCurrencyModel;
import com.currency.convertor.repository.CurrencyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    private final CurrencyRepository currencyRepository;

    public AdminServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public CurrencyExchange updateCurrency(UpdateCurrencyModel model, User user) {
        if (!user.getRoles().equals(ERole.ROLE_ADMIN)) {
            throw new IllegalArgumentException("have not rights");
        }
        if (model.getCurrencyName().length() < 3 && model.getCurrencyName().length() > 3) {
            throw new IllegalArgumentException("Currency name must be 3 length");
        }
        CurrencyExchange currencyExchange = new CurrencyExchange();
        currencyExchange.setNameOfValue(model.getCurrencyName());
        currencyExchange.setRefreshTime(LocalDate.now());
        return this.currencyRepository.saveAndFlush(currencyExchange);
    }

    @Override
    public void deletedCurrency(String currencyName,User user) {

        if (!user.getRoles().equals(ERole.ROLE_ADMIN)) {
            throw new IllegalArgumentException("have not rights");
        }
        CurrencyExchange currencyExchange = this.currencyRepository.findByNameOfValue(currencyName);
        if (currencyExchange == null) {
            throw new IllegalArgumentException("have not contains this currency");
        }
        this.currencyRepository.delete(currencyExchange);
        logger.info("successful deleted this currency = "+ currencyName);
    }
}
