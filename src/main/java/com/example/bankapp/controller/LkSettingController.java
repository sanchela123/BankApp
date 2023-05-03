package com.example.bankapp.controller;

import com.example.bankapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LkSettingController {
    @Autowired
    AccountRepository accountRepository;


}
