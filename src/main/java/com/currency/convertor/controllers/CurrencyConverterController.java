package com.currency.convertor.controllers;

import com.currency.convertor.domain.entity.User;
import com.currency.convertor.domain.model.CurrencyRequestModel;
import com.currency.convertor.service.currency.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<?> changeCurrency(@RequestBody CurrencyRequestModel model, @AuthenticationPrincipal User user) {
        return new ResponseEntity<>(currencyService.convert(model,user), HttpStatus.ACCEPTED);
    }

    @GetMapping("/currencies")
    public List getCurrency() {
        return this.currencyService.getCurrencyRate();
    }
}
