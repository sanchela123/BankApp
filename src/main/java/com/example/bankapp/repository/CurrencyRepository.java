package com.example.bankapp.repository;

import com.example.bankapp.entity.change.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {


}
