package com.currency.convertor.controllers;

import com.currency.convertor.domain.entity.User;
import com.currency.convertor.service.history.HistoryService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/all")
    public List getAllHistory (@AuthenticationPrincipal User user){
      return   this.historyService.getAllHistory(user);
    }
}
