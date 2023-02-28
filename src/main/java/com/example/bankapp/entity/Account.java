package com.example.bankapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Time;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "bank_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Long account_number;
    @NonNull
    @Size(min = 5,message = "Username must been more than 5 characters" )
    private String login;
    @NonNull
    @Size(min = 5,message = "Password must been more than 5 characters" )
    private String password;
    @Transient
    private String passwordConfirm;
    @NonNull
    private Time cr_time;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Card> cards;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Contribution> contributions;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Credit> credits;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<External_transactions> ex_transactions;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Internal_transactions> int_transactions;

}
