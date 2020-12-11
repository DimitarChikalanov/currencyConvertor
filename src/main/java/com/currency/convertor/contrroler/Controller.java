package com.currency.convertor.contrroler;

import com.currency.convertor.domain.model.CurrencyRequestModel;
import com.currency.convertor.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class Controller {

    private final CurrencyService currencyService;

    public Controller(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping("/change")
    public ResponseEntity<?> changeCurrency(@RequestBody CurrencyRequestModel model) {
        return new ResponseEntity<>(currencyService.convert(model), HttpStatus.ACCEPTED);
    }

    @GetMapping("/get_currency")
    public Map getCurrency(@RequestParam String currencyName) {
        return this.currencyService.getCurrencyRate(currencyName);
    }
}
