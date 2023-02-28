package com.example.bankapp.service.generator;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Component
public class Generator {
    volatile Long firstCardNumber = 100000000000000L;
    Long maxCardNumber = 9999999999999999L;
    volatile Long firstCheckNumber = 100000000000L;
    Long maxCheckNumber = 9999999999999L;


    Set<Long> deleteCardNumber = Collections.synchronizedSet(new HashSet<>());
    Set<Long> deleteCheckNumber = Collections.synchronizedSet(new HashSet<>());

    class OutOfRange extends Exception {

    }


    public Long GenerateCardNumber() throws OutOfRange {
        Long CardNumForDelit;
        //Проверяем пул для номера карты
        if (!deleteCardNumber.isEmpty()) {
            CardNumForDelit = deleteCardNumber.stream().findFirst().orElse(-1L);
            deleteCardNumber.remove(CardNumForDelit);
            return CardNumForDelit;
        }
        if (firstCardNumber++ == maxCardNumber)
            throw new OutOfRange();
        firstCardNumber++;
        return firstCardNumber;
    }

    public Long GenerateCheckNumber() throws OutOfRange {
        Long CheckNumForDelit;
        //Проверяем пул свободных номеров
        if (!deleteCardNumber.isEmpty()) {
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
