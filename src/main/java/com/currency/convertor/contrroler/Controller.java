package com.currency.convertor.contrroler;

import com.currency.convertor.domain.model.CurrencyRequestModel;
import com.currency.convertor.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final CurrencyService currencyService;

    public Controller(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping("/us")
    public ResponseEntity<?> sent (CurrencyRequestModel model){
        return new ResponseEntity<>(currencyService.convert(model), HttpStatus.ACCEPTED);
    }
}
