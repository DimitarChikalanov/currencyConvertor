package com.currency.convertor.controllers;

import com.currency.convertor.domain.model.CurrencyRequestModel;
import com.currency.convertor.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD:src/main/java/com/currency/convertor/controllers/CurrencyConverterController.java
=======

import java.util.List;
import java.util.Map;
>>>>>>> 69db28baf9a7ac3db3b2148e7ea9a6ee1e2662d1:src/main/java/com/currency/convertor/contrroler/Controller.java

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

    @GetMapping("/get_all_currency")
    public List getCurrency() {
        return this.currencyService.getCurrencyRate();
    }
}
