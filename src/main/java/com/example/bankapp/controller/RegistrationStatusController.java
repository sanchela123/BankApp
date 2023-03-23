package com.example.bankapp.controller;


import com.example.bankapp.exchangeRateService.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


//Add only for test, delete latter!

@Controller
public class RegistrationStatusController {

    @Autowired
    Proxy proxy;

    @GetMapping("/registration_status")
    public String status(Model model){
        model.addAttribute("CurrencyForm", proxy.toString());

        System.out.println("Current course:" + proxy.toString() );
        return "registration_status";
    }

}
