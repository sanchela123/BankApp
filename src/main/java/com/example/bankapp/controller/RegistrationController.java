package com.example.bankapp.controller;

import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Address;
import com.example.bankapp.service.RegistrationService;
import com.example.bankapp.service.generator.Generator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("registrationFormUserAccount", new Account());
        return "registration";

    }
    @PostMapping("/registration")
    public String addUserAndAccount(@ModelAttribute("registrationFormUserAccount") @Valid Account accountForm,
                                    BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            if (accountForm.getLogin().length()<5)
                model.addAttribute("loginErrorValidate", "Логин должен быть больше 5 симоволов");
            if (accountForm.getPassword().length()<5)
                model.addAttribute("passwordErrorValidate", "Пароль должен быть больше 5 симоволов");
            return "registration";
        }
        if(!accountForm.getPassword().equals(accountForm.getPasswordConfirm())){
            model.addAttribute("passwordError","Пароли не совпадают");
            return "registration";
        }
        try {
            if(!registrationService.saveAccount(accountForm)){
                model.addAttribute("loginError", "Пользователь с таким логином уже существет, пожалуйста придумайте другой");
                return "registration";
            }
        } catch (Generator.OutOfRange e) {
            throw new RuntimeException(e);
        }
        return "redirect:/registration_status";
    }


}
