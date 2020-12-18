package com.currency.convertor.controllers;

import com.currency.convertor.domain.entity.User;
import com.currency.convertor.domain.model.UpdateCurrencyModel;
import com.currency.convertor.service.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCurrency(UpdateCurrencyModel model, @AuthenticationPrincipal User user) {
        return new ResponseEntity<>(this.adminService.updateCurrency(model, user), HttpStatus.ACCEPTED);
    }
}
