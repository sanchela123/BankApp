package com.example.bankapp.controller;


import com.example.bankapp.repository.CardRepository;
import com.example.bankapp.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManageController {

    @Autowired
   private CardService cardService;

    @Autowired
    private CardRepository cardRepository;

    @GetMapping("/manage")
    public String currentUser(Model model){
        model.addAttribute("CardForm", cardRepository.findAllById(cardService.getAccountId()));

        return "manage";}
}
