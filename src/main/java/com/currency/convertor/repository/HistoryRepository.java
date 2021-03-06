package com.currency.convertor.repository;

import com.currency.convertor.domain.entity.History;
import com.currency.convertor.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History,Long> {

    List<History> findAllByUser(User user);

    List<History> findAllByUserAndExchangedAt(User user, LocalDate time);

}
