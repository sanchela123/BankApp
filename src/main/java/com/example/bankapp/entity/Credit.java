package com.example.bankapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "bank_credit")
public class Credit {
    @Id
    private Long credit_number;


    private Long mnamount;


    private Date dateexpr;

    private Date createdate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
}
