package com.example.bankapp.repository;

import com.example.bankapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByLogin(String login);


    Account findAccountByAccountnumber(Long accountnumber);


}
