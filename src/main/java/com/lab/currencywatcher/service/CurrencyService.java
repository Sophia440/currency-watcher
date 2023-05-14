package com.lab.currencywatcher.service;

import com.lab.currencywatcher.dto.CurrencyDto;
import com.lab.currencywatcher.model.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    List<Currency> findAll();

    Optional<Currency> findById(Integer id);

    Optional<Currency> findBySymbol(String symbol);

    void saveAll(List<Currency> currencies);

    List<CurrencyDto> findAllDtos();

    void initialLoad();

    void updatePrices();
}
