package com.example.bankapp.entity.change;

import com.example.bankapp.entity.Account;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@Data
@Entity
@Table(name = "bank_currency")
public class Currency {
    @Id
    private Long id;
    private String name;
    private Date date;
    private Double currentValue;
    private Double sellValue;
    private Double buyValue;
}
