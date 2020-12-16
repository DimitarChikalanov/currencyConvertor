package com.currency.convertor.service.history;

import com.currency.convertor.domain.entity.User;

import java.util.List;

public interface HistoryService {

    List getAllHistory(User user);
}
