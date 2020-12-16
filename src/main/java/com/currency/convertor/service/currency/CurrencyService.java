package com.currency.convertor.service.currency;

import com.currency.convertor.domain.entity.User;
import com.currency.convertor.domain.model.CurrencyRequestModel;

import java.math.BigDecimal;
import java.util.List;

public interface CurrencyService {

    BigDecimal convert(CurrencyRequestModel model, User user);

    List getCurrencyRate();
}
