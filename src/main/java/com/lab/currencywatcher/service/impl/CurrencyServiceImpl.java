package com.lab.currencywatcher.service.impl;

import com.lab.currencywatcher.dto.CurrencyDto;
import com.lab.currencywatcher.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final WebClient client = WebClient.builder().build();
    private WebClient webClient() {
        return client.mutate().baseUrl("https://api.coinlore.net/api").build();
    }

    @Override
    public List<CurrencyDto> getCurrency(String currencyId) {
        return webClient().get()
                .uri(uriBuilder -> uriBuilder.path("/ticker/")
                        .queryParam("id", currencyId)
                        .build())
                .retrieve()
                .bodyToFlux(CurrencyDto.class).collectList().block();
    }
}
