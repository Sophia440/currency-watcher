package com.lab.currencywatcher.service;

import com.lab.currencywatcher.dto.CurrencyDto;

import java.util.List;

public interface ThirdPartyClient {
    List<CurrencyDto> getCurrency(String currencyId);
}
