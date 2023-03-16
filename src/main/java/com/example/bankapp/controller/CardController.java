package com.example.bankapp.controller;


import com.example.bankapp.entity.Card;
import com.example.bankapp.repository.CardRepository;
import com.example.bankapp.service.CardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CardController {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    CardService cardService;

    @GetMapping("/newcard")
    public String cardcreation(Model model){
        model.addAttribute("CardForm", cardRepository.findAllByAccountId(cardService.getAccountId()));
        model.addAttribute("NewCardForm", new Card());
        return "newcard";
    }

    @PostMapping("/newcard")
    public String addNewCard(@ModelAttribute("NewCardForm") @Valid Card cardForm, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "newcard";
        }
        if(!cardService.saveCard(cardForm))
            return "newcard";
        return "redirect:/manage";
    }

}
