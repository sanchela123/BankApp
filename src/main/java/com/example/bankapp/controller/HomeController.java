package com.example.bankapp.controller;


import com.example.bankapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/lk")
    public String currentUser(){return "lk";}


}
