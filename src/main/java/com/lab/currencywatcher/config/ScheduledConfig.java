package com.lab.currencywatcher.config;

import com.lab.currencywatcher.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Configuration
@EnableScheduling
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ScheduledConfig implements SmartInitializingSingleton {

    private final CurrencyService currencyService;

    @Override
    public void afterSingletonsInstantiated() {
        if (currencyService.findAll().isEmpty()) {
            currencyService.initialLoad();
        }
    }

    @Scheduled(cron = "${currency.price.refresh.cron}")
    public void refreshCurrencyPrices() {
        log.info("refreshCurrencyPrices starting at {}", LocalDateTime.now());
        currencyService.updatePrices();
        log.info("refreshCurrencyPrices completed at: {}", LocalDateTime.now());
    }
}
