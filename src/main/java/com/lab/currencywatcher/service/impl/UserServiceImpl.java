package com.lab.currencywatcher.service.impl;

import com.lab.currencywatcher.model.UserRecord;
import com.lab.currencywatcher.repository.UserRecordRepo;
import com.lab.currencywatcher.service.CurrencyService;
import com.lab.currencywatcher.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final CurrencyService currencyService;
    private final UserRecordRepo userRecordRepo;

    public void registerPrice(String username, String symbol) {
        var currency = currencyService.findBySymbol(symbol);
        if (currency.isPresent()) {
            var userRecord = UserRecord.builder()
                    .username(username)
                    .currency(currency.get())
                    .registeredPriceUsd(currency.get().getPriceUsd())
                    .build();
            save(userRecord);
            log.info("User record for {} and {} saved successfully", username, symbol);
        } else {
            log.error("User record for {} failed: currency {} not found", username, symbol);
        }
    }

    public void verifyPrices() {
        userRecordRepo.findAll().forEach(record -> {
            var currency = currencyService.findById(record.getCurrency().getId());
            if (currency.isPresent()) {
                var priceDifference = calculatePriceDifferencePercent(record.getRegisteredPriceUsd(), currency.get().getPriceUsd());
                if (priceDifference.compareTo(BigDecimal.ONE) >= 0) {
                    log.warn("The price for {} for user {} changed by {} percent", currency.get().getSymbol(), record.getUsername(),
                            priceDifference);
                }
            } else {
                log.error("Verifying price for {} failed: currency {} not found", record.getUsername(),
                        record.getCurrency().getId());
            }
        });
    }

    private BigDecimal calculatePriceDifferencePercent(BigDecimal registered, BigDecimal current) {
        var difference = registered.subtract(current);
        return difference.divide(registered, RoundingMode.DOWN).multiply(BigDecimal.valueOf(100L)).abs();
    }

    private void save(UserRecord userRecord) {
        userRecordRepo.save(userRecord);
    }
}
