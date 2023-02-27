package com.example.bankapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
@Table(name = "bank_user")
public class User {
    @Id
    @Column(name = "user_id")
    private Long id;
    @Size(min = 6, max = 6, message = "The number must contain 6 digits")
    private Long phone_number;
    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Address address;
    @Email
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private Account account;
}
