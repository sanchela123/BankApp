package com.example.bankapp.repository;

import com.example.bankapp.entity.External_transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionExternalRepository extends JpaRepository<External_transactions, Long> {
}
