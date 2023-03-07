package com.example.bankapp.service.generator;


import com.example.bankapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;


@Service

public class Generator {

    @Autowired
    AccountRepository accountRepository;

    Long firstCardNumber = 100000000000000L;
    Long maxCardNumber = 9999999999999999L;
    Long firstCheckNumber = 100000000000L;
    Long maxCheckNumber = 9999999999999L;



    Set<Long> deleteCardNumber = Collections.synchronizedSet(new HashSet<>());
    Set<Long> deleteCheckNumber = Collections.synchronizedSet(new HashSet<>());
    Set<Long> AllCardNumber = Collections.synchronizedSet(new HashSet<>());


    public class OutOfRange extends Exception {

    }
    public Date CreationTime(){
        return new Date();
    }

    public Long GenerateCardNumber() throws OutOfRange {
  //      AllCardNumber = accountRepository.getAllByAccount_number();
        //Проверяем пул для номера карты

        if (firstCardNumber++ == maxCardNumber)
            throw new OutOfRange();
        firstCardNumber++;
        return firstCardNumber;
    }

    public Long GenerateCheckNumber() throws OutOfRange {
        Long CheckNumForDelit;
        //Проверяем пул свободных номеров
        if (!deleteCheckNumber.isEmpty()) {
            CheckNumForDelit = deleteCheckNumber.stream().findFirst().orElse(-1L);
            deleteCheckNumber.remove(CheckNumForDelit);
            return CheckNumForDelit;
        }
        //Проверяем пул для номера счета
        if (firstCheckNumber++ == maxCheckNumber)
            throw new OutOfRange();
        firstCheckNumber++;
        return firstCheckNumber;
    }
}
