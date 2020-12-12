package com.currency.convertor.repository;

import com.currency.convertor.domain.entity.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyExchange, Long> {

    CurrencyExchange findByNameOfValue(String name);
}
