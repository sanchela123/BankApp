package com.example.bankapp.controller;

import com.example.bankapp.entity.Account;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.service.LkSettingsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LkSettingController {

    @Autowired
    LkSettingsService lkSettingsService;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/lksettings")
    public String changeoptions(Model model){
        model.addAttribute("newAccountOptions", new Account());
        return "lksettings";
    }

    @PostMapping("/lksettings")
    public String changeAccountOptions(@ModelAttribute("newAccountOptions") @Valid Account newAccountForm,
                                       BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()&&(newAccountForm.getLogin()!=""&&newAccountForm.getPassword()!="")){
            System.out.println(newAccountForm.getLogin());
            System.out.println(newAccountForm.getPassword());
            if (newAccountForm.getLogin().length()<5)
                model.addAttribute("loginErrorValidate", "Логин должен быть больше 5 симоволов");
            if (newAccountForm.getPassword().length()<5)
                model.addAttribute("passwordErrorValidate", "Пароль должен быть больше 5 симоволов");
            return "lksettings";
        }
        if(!newAccountForm.getPassword().equals(newAccountForm.getPasswordConfirm())){
            model.addAttribute("passwordError","Пароли не совпадают");
            return "lksettings";
        }

        if(!lkSettingsService.changeAccountOptions(newAccountForm)){
            model.addAttribute("loginError", "Пользователь с таким логином уже существет, пожалуйста придумайте другой");
            return "lksettings";
        }
        return "redirect:/lk";
    }
}
