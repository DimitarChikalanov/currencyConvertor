package com.currency.convertor.service.history;

import com.currency.convertor.domain.entity.User;
import com.currency.convertor.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public List getAllHistory(User user) {
        return this.historyRepository.findAllByUser(user);
    }
}
