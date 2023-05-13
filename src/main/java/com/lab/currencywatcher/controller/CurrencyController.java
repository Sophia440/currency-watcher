package com.lab.currencywatcher.controller;

import com.lab.currencywatcher.controller.api.CurrencyControllerApi;
import com.lab.currencywatcher.dto.CurrencyDto;
import com.lab.currencywatcher.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CurrencyController.PATH)
public class CurrencyController implements CurrencyControllerApi {

    public static final String PATH = "/currencies";

    private final CurrencyService currencyService;

    @GetMapping
    public List<CurrencyDto> getCurrencies() {
        var response = currencyService.getCurrency("90");
        return List.of(response.get(0));
    }

    @GetMapping("/{symbol}")
    public BigDecimal getPrice(@PathVariable String symbol) {
        return BigDecimal.ONE;
    }

    @PostMapping("/notify")
    public void registerUserPrice(String username, String symbol) {

    }

}
