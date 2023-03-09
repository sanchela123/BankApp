package com.example.bankapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "bank_contribution")
public class Contribution {

    @Id
    private Long contr_id;

    private Long mnamount;

    @NotNull
    private Date dateexpr;

    @NotNull
    private Date datecreate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
}
