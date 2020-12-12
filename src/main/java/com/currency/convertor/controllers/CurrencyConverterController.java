package com.currency.convertor.controllers;

import com.currency.convertor.domain.model.CurrencyRequestModel;
import com.currency.convertor.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class CurrencyConverterController {

    private final CurrencyService currencyService;

    public CurrencyConverterController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping("/change")
    public ResponseEntity<?> changeCurrency(@RequestBody CurrencyRequestModel model) {
        return new ResponseEntity<>(currencyService.convert(model), HttpStatus.ACCEPTED);
    }

    @GetMapping("/currencies")
    public List getCurrency() {
        return this.currencyService.getCurrencyRate();
    }
}
