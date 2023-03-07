package com.example.bankapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Data
@Entity
@Table(name = "bank_role")
@NoArgsConstructor

public class Role implements GrantedAuthority {
    @Id
    private Long id;

    private String name;


    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<Account> accounts;

    public Role(Long id){
        this.id= id;
    }

    public Role(Long id, String name){
        this.id = id;
        this.name= name;
    }


    @Override
    public String getAuthority() {
        return getName();
    }
}
