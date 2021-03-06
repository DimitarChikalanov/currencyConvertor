package com.currency.convertor.service.admin;

import com.currency.convertor.domain.entity.CurrencyExchange;
import com.currency.convertor.domain.entity.User;

import com.currency.convertor.domain.model.update.UpdateCurrencyModel;

public interface AdminService {

    CurrencyExchange updateCurrency(UpdateCurrencyModel model, User user);

    void deletedCurrency(String currencyName, User user);

}
