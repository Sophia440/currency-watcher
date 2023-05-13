package com.lab.currencywatcher.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDto {

    private Integer id;

    private String symbol;

    private String name;

    @JsonProperty("price_usd")
    private BigDecimal priceUsd;

}
