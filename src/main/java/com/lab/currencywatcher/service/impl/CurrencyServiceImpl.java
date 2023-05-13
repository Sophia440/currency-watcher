package com.lab.currencywatcher.service.impl;

import com.lab.currencywatcher.dto.CurrencyDto;
import com.lab.currencywatcher.mapper.CurrencyMapper;
import com.lab.currencywatcher.model.Currency;
import com.lab.currencywatcher.repository.CurrencyRepo;
import com.lab.currencywatcher.service.CurrencyService;
import com.lab.currencywatcher.service.ThirdPartyClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Value("#{${currency.available}}")
    private Map<Integer, String> availableCurrencies;
    private final ThirdPartyClient thirdPartyClient;
    private final CurrencyMapper currencyMapper;
    private final CurrencyRepo currencyRepo;

    public List<Currency> findAll() {
        return currencyRepo.findAll();
    }

    public Optional<Currency> findById(Integer id) {
        return currencyRepo.findById(id);
    }

    public void save(Currency currency) {
        currencyRepo.save(currency);
    }

    public void saveAll(List<Currency> currencies) {
        currencyRepo.saveAll(currencies);
    }

    public List<CurrencyDto> findAllDtos() {
        return findAll().stream()
                .map(currencyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void initialLoad() {
        log.info("Loading currency prices for the first time");
        var response = availableCurrencies.keySet().stream()
                .map(Object::toString)
                .map(thirdPartyClient::getCurrency)
                .flatMap(List::stream)
                .map(currencyMapper::toEntity)
                .collect(Collectors.toList());
        saveAll(response);
        log.info("Initial currency prices load finished");
    }


    @Transactional
    public void updatePrices() {
        log.info("Updating currencies started");
        availableCurrencies.keySet().stream()
                .map(Object::toString)
                .forEach(id -> {
                    var updateValue = thirdPartyClient.getCurrency(id);
                    var currencyOpt = findById(Integer.valueOf(id));
                    if (currencyOpt.isPresent()) {
                        var currency = currencyOpt.get();
                        currency.setPriceUsd(updateValue.get(0).getPriceUsd());
                        save(currency);
                    }
                });
        log.info("Updating currencies finished");
    }

}
