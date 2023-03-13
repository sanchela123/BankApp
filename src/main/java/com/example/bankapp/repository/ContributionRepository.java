package com.example.bankapp.repository;


import com.example.bankapp.entity.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContributionRepository extends JpaRepository<Contribution, Long> {

    List<Contribution> findAllByAccountId(Long id);
}
