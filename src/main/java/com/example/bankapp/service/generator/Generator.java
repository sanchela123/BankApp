package com.example.bankapp.service.generator;


import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.CardRepository;
import com.example.bankapp.repository.ContributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.sql.Timestamp;
import java.util.*;


@Service

public class Generator {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    ContributionRepository contributionRepository;

    Long firstAccountNumber = 100000000000000L;
    Long maxAccountNumber = 9999999999999999L;
    Long generatedNumber;
    Long firstCheckNumberContribution = 100000000000L;
    Long maxCheckNumberContribution =  3333333333333L;
    Long firstCheckNumberCard = 3333333333334L;
    Long maxCheckNumberCard = 6666666666666l;
    Long minCheckNumberCredit = 6666666666667l;
    Long maxCheckNumberCredit = 9999999999999L;


    Random random = new Random();
    public Date CreationTime(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 4);
        return cal.getTime();
    }
    public Date ValidTimeForCard(){
        return new Date();
    }

    //Уникальный номер аккаунта пользователя
    public Long GenerateAccountNumber() {
        generatedNumber = random.nextLong(maxAccountNumber - firstAccountNumber)+ firstAccountNumber;
        if(accountRepository.findAccountByAccountnumber(generatedNumber)!=null)
            return GenerateAccountNumber();
        return generatedNumber;
    }

    public Long GenerateContributionNumber() {
        generatedNumber = random.nextLong(maxCheckNumberContribution - firstCheckNumberContribution)+ firstCheckNumberContribution;
        if(contributionRepository.findById(generatedNumber)!=null)
            return GenerateContributionNumber();
        return generatedNumber;
    }
    public Long GenerateCardNumber()  {
        generatedNumber = random.nextLong(maxCheckNumberCard - firstCheckNumberCard)+ firstCheckNumberCard;
        if(cardRepository.findCardByCardnumber(generatedNumber)!=null)
            return GenerateCardNumber();
        return generatedNumber;
    }
    public Long GenerateCreditNumber()  {
        generatedNumber = random.nextLong(maxCheckNumberCredit - minCheckNumberCredit)+ minCheckNumberCredit;
        if(accountRepository.findAccountByAccountnumber(generatedNumber)!=null)
            return GenerateCreditNumber();
        return generatedNumber;
    }

}
