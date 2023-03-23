package com.example.bankapp.entity.change;

import com.example.bankapp.entity.Account;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class Currency {
    private Long id;
    private String name;
    private Double currentValue;
    private Double sellValue;
    private Double buyValue;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ratesid")
    private Rates rates;
}
