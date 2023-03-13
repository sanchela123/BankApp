package com.example.bankapp.repository;

import com.example.bankapp.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findAllByAccountId(Long id);

}
