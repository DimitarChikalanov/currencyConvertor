package com.currency.convertor.repository;

import com.currency.convertor.domain.entity.CurrencyToDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyToDay, Long> {

    CurrencyToDay findByNameOfValue(String name);
}
