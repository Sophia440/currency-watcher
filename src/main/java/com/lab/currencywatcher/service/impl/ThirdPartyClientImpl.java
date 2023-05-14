package com.lab.currencywatcher.service.impl;

import com.lab.currencywatcher.dto.CurrencyDto;
import com.lab.currencywatcher.service.ThirdPartyClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ThirdPartyClientImpl implements ThirdPartyClient {

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
