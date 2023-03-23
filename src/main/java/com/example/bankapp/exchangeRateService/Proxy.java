package com.example.bankapp.exchangeRateService;

import com.example.bankapp.entity.change.Rates;
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

@Service
@EnableScheduling
public class Proxy {

       @Autowired
        Generator generator;
        Rates rates = new Rates();
        ObjectMapper mapper = new ObjectMapper();
        String url = "https://www.cbr-xml-daily.ru/daily_json.js";
        RestTemplate restTemplate = new RestTemplate();
        String string = restTemplate.getForObject(url, String.class);
        JsonNode jsonNode;

            {
                try {
                    jsonNode = mapper.readTree(string);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }


    @Scheduled(fixedRate = 10000)
    public Rates refreshDate(){
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
        return rates;
    }

    @Override
    public String toString() {
        return "Current values: "+ rates.getUsdvalue() + " " +rates.getEurvalue()
                + " " + rates.getCurrentdate() ;
    }
}
