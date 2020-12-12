package com.currency.convertor.repository;

import com.currency.convertor.domain.model.ResponseCurrencyModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "currencyApi", url = "https://api.exchangeratesapi.io")
public interface CurrencyApiClient {

    @GetMapping("/latest")
    ResponseCurrencyModel getLatest();
}
