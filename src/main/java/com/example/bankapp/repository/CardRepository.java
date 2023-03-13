package com.example.bankapp.repository;

import com.example.bankapp.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card findCardByCardnumber(Long cardnumber);

    List<Card> findAllByAccountId(Long id);
}
