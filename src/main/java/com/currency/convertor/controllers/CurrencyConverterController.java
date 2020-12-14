package com.currency.convertor.controllers;

import com.currency.convertor.domain.model.CurrencyRequestModel;
import com.currency.convertor.service.currency.CurrencyService;
import com.currency.convertor.service.currency.CurrencyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class CurrencyConverterController {

    private final CurrencyService currencyService;

    @Autowired
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
