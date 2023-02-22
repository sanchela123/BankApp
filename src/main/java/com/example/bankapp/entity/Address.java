package com.example.bankapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "bank_address")
public class Address {
    @Id
    private Long address_id;

    @NotNull
    @Size(min = 5, message = "Country name must been more than 5 characters")
    private String country;
    @NotNull
    @Size(min = 5, message = "Country name must been more than 5 characters")
    private String city;
    @NotNull
    private int house;

    private int apartment;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "address_id")
    private User user;
}
