package com.example.bankapp.repository;

import com.example.bankapp.entity.Internal_transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionInternalRepository extends JpaRepository<Internal_transactions, Long> {
}
