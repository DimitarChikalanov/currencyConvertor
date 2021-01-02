package com.currency.convertor.service.history;

import com.currency.convertor.domain.entity.User;
import com.currency.convertor.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public List getAllHistory(User user) {
        return this.historyRepository.findAllByUser(user);
    }

    // za fix problem v parse
    @Override
    public List getAllHistoryByFromData(User user, String time) {
        return this.historyRepository.findAllByUserAndExchangedAt(user, LocalDate.parse(time, formatter));
    }

}
