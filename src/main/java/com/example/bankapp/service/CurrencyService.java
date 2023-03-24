package com.example.bankapp.service;


import com.example.bankapp.entity.change.Currency;
import com.example.bankapp.exchangeRateService.Proxy;
import com.example.bankapp.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    Proxy proxy;


    public void saveCurrentRate(Currency currencyUSD, Currency currencyEUR){
        currencyUSD.setCurrentValue(proxy.currentUSD());
        currencyUSD.setBuyValue(proxy.currentUSD()-2);
        currencyUSD.setSellValue(proxy.currentUSD()+2);
        currencyUSD.setDate(proxy.lastUpdate());
        currencyEUR.setCurrentValue(proxy.cuurentEUR());
        currencyEUR.setBuyValue(proxy.currentUSD()-2);
        currencyEUR.setSellValue(proxy.currentUSD()+2);
        currencyEUR.setDate(proxy.lastUpdate());
        currencyRepository.save(currencyEUR);
        currencyRepository.save(currencyUSD);
    }
}
