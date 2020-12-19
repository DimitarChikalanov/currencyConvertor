package com.currency.convertor.controllers;

import com.currency.convertor.domain.entity.User;
import com.currency.convertor.service.history.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/history")
public class HistoryController {

    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/all")
    public List getAllHistory (@AuthenticationPrincipal User user){
      return   this.historyService.getAllHistory(user);
    }

    @PostMapping("/fromdata")
    public ResponseEntity getAllHistoryFromData(@RequestBody String time, @AuthenticationPrincipal User user){
        return new ResponseEntity(this.historyService.getAllHistoryByFromData(user,time), HttpStatus.ACCEPTED);
    }

}
