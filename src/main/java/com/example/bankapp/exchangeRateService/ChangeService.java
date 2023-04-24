package com.example.bankapp.exchangeRateService;

import com.example.bankapp.entity.change.Currency;
import com.example.bankapp.entity.change.Rates;
import com.example.bankapp.repository.CurrencyRepository;
import com.example.bankapp.service.generator.Generator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@EnableScheduling
public class ChangeService {


    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    Generator generator;

    Double CurrentUSDValue;
    Double CurrentEURValue;
    ObjectMapper mapper = new ObjectMapper();
    String url = "https://www.cbr-xml-daily.ru/daily_json.js";
    RestTemplate restTemplate = new RestTemplate();
    String string = restTemplate.getForObject(url, String.class);
    JsonNode jsonNode;


    //Update once an hour
    @Scheduled(fixedRate = 3600000)
    public void refreshDate() {
        url = "https://www.cbr-xml-daily.ru/daily_json.js";
        string = restTemplate.getForObject(url, String.class);
        {
            try {
                jsonNode = mapper.readTree(string);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        CurrentUSDValue = Double.valueOf(jsonNode.get("Valute").get("USD").get("Value").toString());
        CurrentEURValue = Double.valueOf(jsonNode.get("Valute").get("EUR").get("Value").toString());
        Currency currencyUSD = new Currency();
        Currency currencyEUR = new Currency();
        currencyUSD.setId(1L);
        currencyUSD.setCurrentValue(CurrentUSDValue);
        currencyUSD.setBuyValue(CurrentUSDValue-CurrentEURValue*0.1);
        currencyUSD.setSellValue(CurrentUSDValue+CurrentUSDValue*0.1);
        currencyUSD.setDate(generator.CreationTime());
        currencyEUR.setId(2L);
        currencyEUR.setCurrentValue(CurrentEURValue);
        currencyEUR.setBuyValue(CurrentEURValue-CurrentEURValue*0.1);
        currencyEUR.setSellValue(CurrentEURValue+CurrentUSDValue*0.1);
        currencyEUR.setDate(generator.CreationTime());
        currencyRepository.save(currencyUSD);
        currencyRepository.save(currencyEUR);
        System.out.println("Обновили Rates!");

    }

}
