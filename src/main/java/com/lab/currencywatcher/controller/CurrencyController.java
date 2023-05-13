package com.lab.currencywatcher.controller;

import com.lab.currencywatcher.controller.api.CurrencyControllerApi;
import com.lab.currencywatcher.dto.CurrencyDto;
import com.lab.currencywatcher.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = CurrencyController.PATH)
public class CurrencyController implements CurrencyControllerApi {

    public static final String PATH = "/currencies";

    private final CurrencyService currencyService;

    @GetMapping
    public List<CurrencyDto> getCurrencies() {
        log.info("Getting all currencies");
        return currencyService.findAllDtos();
    }

    @GetMapping("/{symbol}")
    public BigDecimal getPrice(@PathVariable String symbol) {
        log.info("Getting the price for {}", symbol);
        return BigDecimal.ONE;
    }

    @PostMapping("/notify")
    public void registerUserPrice(String username, String symbol) {
        log.info("Registering the price for user {} and currency {}", username, symbol);
    }

}
