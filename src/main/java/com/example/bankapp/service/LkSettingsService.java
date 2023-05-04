package com.example.bankapp.service;

import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Role;
import com.example.bankapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
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
    public Account getCurrentAccount(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = (Account) authentication.getPrincipal();
        return account;
    }

    public boolean changeAccountOptions(Account newAccountOptions){
        Account oldaccountoptions = getCurrentAccount();
        Account CheckAccountInBD = accountRepository.findByLogin(newAccountOptions.getLogin());
        //immutable options
        newAccountOptions.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        newAccountOptions.setAccountnumber(oldaccountoptions.getAccountnumber());
        newAccountOptions.setCr_time(oldaccountoptions.getCr_time());
        newAccountOptions.setId(oldaccountoptions.getId());
        //changeable options
        if(newAccountOptions.getPassword() == "")
            newAccountOptions.setPassword(oldaccountoptions.getPassword());
            else
            newAccountOptions.setPassword(bCryptPasswordEncoder.encode(newAccountOptions.getPassword()));
        //Дополнительно сообщить ползователю, что логин уже занят?
        if(newAccountOptions.getLogin() == "" || CheckAccountInBD!=null)
            newAccountOptions.setLogin(oldaccountoptions.getLogin());
        if(newAccountOptions.getPhonenumber() == null)
            newAccountOptions.setPhonenumber(oldaccountoptions.getPhonenumber());
        if(newAccountOptions.getEmail() == "")
            newAccountOptions.setEmail(oldaccountoptions.getEmail());
        System.out.println(newAccountOptions);
        accountRepository.save(newAccountOptions);
        return true;
    }
}
