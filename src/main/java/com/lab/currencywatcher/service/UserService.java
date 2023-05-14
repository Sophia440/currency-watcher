package com.lab.currencywatcher.service;

public interface UserService {

    void registerPrice(String username, String symbol);

    void verifyPrices();
}
