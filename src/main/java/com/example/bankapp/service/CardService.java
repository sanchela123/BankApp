package com.example.bankapp.service;

import com.example.bankapp.entity.Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    public Long getAccountId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = (Account) authentication.getPrincipal();
        Long userId = account.getId();
        return userId;
    }

}
