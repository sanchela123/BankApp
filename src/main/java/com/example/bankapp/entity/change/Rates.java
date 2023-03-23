package com.example.bankapp.entity.change;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.Date;
import java.util.Set;


@Data
public class Rates {
    private Date currentdate;
    private Double usdvalue;
    private Double eurvalue;

}
