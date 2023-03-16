package com.example.bankapp.service;


import com.example.bankapp.entity.Credit;
import com.example.bankapp.repository.CreditRepository;
import com.example.bankapp.service.generator.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditService {

    @Autowired
    Generator generator;

    @Autowired
    CreditRepository creditRepository;

    public void saveCredit(Credit credit){
        credit.setCredit_number(generator.GenerateCreditNumber());
        credit.setCreatedate(generator.CreationTime());
        credit.setMnamount(100000000L);
    }
}
