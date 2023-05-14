package com.lab.currencywatcher.controller.api;

import com.lab.currencywatcher.dto.CurrencyDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.math.BigDecimal;
import java.util.List;

public interface CurrencyControllerApi {

    @ApiOperation(value = "Get all currencies.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved currencies.")
    })
    List<CurrencyDto> getCurrencies();

    @ApiOperation(value = "Get current price by currency code (symbol).")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved current price.")
    })
    BigDecimal getPrice(String symbol);

    @ApiOperation(value = "Register a price for a specified username.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered the price.")
    })
    void registerUserPrice(String username, String symbol);
}
