package com.lab.currencywatcher.repository;

import com.lab.currencywatcher.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepo extends JpaRepository<Currency, Integer> {

    @Override
    Optional<Currency> findById(Integer integer);

    Optional<Currency> findBySymbol(String symbol);
}