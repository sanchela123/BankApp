package com.example.bankapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "bank_card")
public class Card {

    @Id
    private Long card_number;

    @NotNull
    private Long card_contribution_number;

    @NotNull
    private Date date_expr;

    private Long mn_amount;

    @NotNull
    private Date date_create;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
}
