package com.currency.convertor.controllers;

import com.currency.convertor.domain.entity.User;
import com.currency.convertor.domain.model.UpdateCurrencyModel;
import com.currency.convertor.service.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCurrency(UpdateCurrencyModel model, @AuthenticationPrincipal User user) {
        return new ResponseEntity<>(this.adminService.updateCurrency(model, user), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public HttpStatus deleteCurrency(String currency, @AuthenticationPrincipal User user) {
        this.adminService.deletedCurrency(currency, user);
        return HttpStatus.ACCEPTED;
    }
}
