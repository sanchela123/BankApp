package com.example.bankapp.service;

import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Role;
import com.example.bankapp.repository.AccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RegistrationService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean saveAccount(Account account){
        Account CheckAccountInBD = accountRepository.findByLogin(account.getLogin());
        if(CheckAccountInBD!=null)
            return false;
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        account.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        accountRepository.save(account);
        return true;
    }

    public boolean deleteAccount(Long accountId){
        if (accountRepository.findById(accountId).isPresent()){
            accountRepository.deleteById(accountId);
            return true;
        }
        return false;
    }

    public List<Account> allAccountList(){
        return accountRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = accountRepository.findByLogin(login);
        if(account == null)
            throw new UsernameNotFoundException("User not found");
        return account;

    }
}
