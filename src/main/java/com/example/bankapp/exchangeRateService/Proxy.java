package com.example.bankapp.exchangeRateService;

import com.example.bankapp.entity.change.Currency;
import com.example.bankapp.entity.change.Rates;
import com.example.bankapp.repository.CurrencyRepository;
import com.example.bankapp.service.CurrencyService;
import com.example.bankapp.service.generator.Generator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Date;
//Получаем данные со стороннего api о текущем курсе валют
//Переписать! Не соотвествует принципам (подумать о сохранении в базу)


//Обновляем данные раз в час (Подумать о времени валидности данных при обмене(если api недоступен)
@Service
@EnableScheduling
public class Proxy {

    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    Generator generator;
    Rates rates = new Rates();
    ObjectMapper mapper = new ObjectMapper();
    String url = "https://www.cbr-xml-daily.ru/daily_json.js";
    RestTemplate restTemplate = new RestTemplate();
    String string = restTemplate.getForObject(url, String.class);
    JsonNode jsonNode;


    //Update once an hour
    @Scheduled(fixedRate = 3600000)
    public Rates refreshDate() {
        url = "https://www.cbr-xml-daily.ru/daily_json.js";
        string = restTemplate.getForObject(url, String.class);
        System.out.println("Date was refresh!");
        {
            try {
                jsonNode = mapper.readTree(string);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        rates.setUsdvalue(Double.valueOf(jsonNode.get("Valute").get("USD").get("Value").toString()));
        rates.setEurvalue(Double.valueOf(jsonNode.get("Valute").get("EUR").get("Value").toString()));
        rates.setCurrentdate(generator.CreationTime());
        System.out.println("Обновили Rates!");
            saveCurrentRate(currencyRepository.getReferenceById(1L),currencyRepository.getReferenceById(2L), rates);

        return rates;
    }

    public Double currentUSD() {
        return rates.getUsdvalue();
    }

    public Double cuurentEUR() {
        return rates.getEurvalue();
    }

    public Date lastUpdate() {
        return rates.getCurrentdate();
    }
    //Почему две разных сущности Rates и Currency (Хотел изначально вынести Rates в DTO, без привязки к базе ???)
    public void saveCurrentRate(Currency currencyUSD, Currency currencyEUR, Rates rates){
        currencyUSD.setCurrentValue(rates.getUsdvalue());
        currencyUSD.setBuyValue(rates.getUsdvalue()-2);
        currencyUSD.setSellValue(rates.getUsdvalue()+2);
        currencyUSD.setDate(rates.getCurrentdate());
        currencyEUR.setCurrentValue(rates.getEurvalue());
        currencyEUR.setBuyValue(rates.getEurvalue()-2);
        currencyEUR.setSellValue(rates.getEurvalue()+2);
        currencyEUR.setDate(rates.getCurrentdate());
        currencyRepository.save(currencyEUR);
        currencyRepository.save(currencyUSD);
    }

    public void saveFirstRates(){
        Currency currency1 = new Currency();
        Currency currency2 = new Currency();
        currency1.setId(1L);
        currency2.setId(2L);
        currencyRepository.save(currency1);
        currencyRepository.save(currency2);
    }


    @Override
    public String toString() {
        return "Current values: " + rates.getUsdvalue() + " " + rates.getEurvalue()
                + " " + rates.getCurrentdate();
    }
}
