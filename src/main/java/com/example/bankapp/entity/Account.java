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

import java.util.Collection;
import java.util.Date;
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

    private Date cr_time;

    private Long phone_number;
    @OneToOne(mappedBy = "account")
    @PrimaryKeyJoinColumn
    private Address address;
    @Email
    private String email;
    @Size(min = 2)
    private String name;

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
        return getRoles();
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String toString(){
        return "login : "+ login +" name: " +name + " email: " + email + " phone: "+ phone_number ;
    }
}
