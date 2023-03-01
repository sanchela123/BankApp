package com.example.bankapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Time;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "bank_account")
public class Account implements UserDetails {
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

    @Size(min = 6, max = 6, message = "The number must contain 6 digits")
    private Long phone_number;
    @OneToOne(mappedBy = "account")
    @PrimaryKeyJoinColumn
    private Address address;
    @Email
    private String email;
    @Size(min = 2)
    private String name;
    @Size(min = 2)
    private String surname;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public Set<Role> getRoles(){
        return roles;
    }



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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
