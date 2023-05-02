package com.example.bankapp.controller;


import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    CurrencyRepository currencyRepository;
    @GetMapping("/lk")
    public String currentUser(Model model) {
        model.addAttribute("CurrencyValue", currencyRepository.findAll());
        return "lk";
    }


}
