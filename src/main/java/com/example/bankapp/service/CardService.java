package com.example.bankapp.service;

import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Card;
import com.example.bankapp.repository.CardRepository;
import com.example.bankapp.service.generator.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    Generator generator;
    @Autowired
    CardRepository cardRepository;

    public Long getAccountId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = (Account) authentication.getPrincipal();
        Long userId = account.getId();
        return userId;
    }


    //В будущем -> отдельный сервис для админов?
    public boolean saveCard(Card card){
        card.setDatecreate(generator.CreationTime());
        card.setCardnumber(generator.GenerateCardNumber());
        card.setMnamount(0L);
        cardRepository.save(card);
        return true;
    }


}
