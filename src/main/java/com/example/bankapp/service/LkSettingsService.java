package com.example.bankapp.service;

import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Role;
import com.example.bankapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

public class LkSettingsService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long getAccountId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = (Account) authentication.getPrincipal();
        Long userId = account.getId();
        return userId;
    }

    public boolean changeAccountOptions(Account oldaccountoptions, Account newaccounoptions){
        Account CheckAccountInBD = accountRepository.findByLogin(newaccounoptions.getLogin());
        //immutable options
        newaccounoptions.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        newaccounoptions.setAccountnumber(oldaccountoptions.getAccountnumber());
        newaccounoptions.setCr_time(oldaccountoptions.getCr_time());
        newaccounoptions.setId(oldaccountoptions.getId());
        newaccounoptions.setPassword(bCryptPasswordEncoder.encode(newaccounoptions.getPassword()));
        //changeable options
        if(newaccounoptions.getPassword() == null)
            newaccounoptions.setPassword(oldaccountoptions.getPassword());
        //Дополнительно сообщить ползователю, что логин уже занят?
        if(newaccounoptions.getLogin() == null || CheckAccountInBD!=null)
            newaccounoptions.setLogin(oldaccountoptions.getLogin());
        if(newaccounoptions.getPhonenumber() == null)
            newaccounoptions.setPhonenumber(oldaccountoptions.getPhonenumber());
        System.out.println(newaccounoptions);
        accountRepository.save(newaccounoptions);
        return true;
    }
}
