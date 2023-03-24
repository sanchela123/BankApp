package com.example.bankapp.entity.change;


import lombok.Data;



import java.util.Date;

@Data
public class Rates {
    private Date currentdate;
    private Double usdvalue;
    private Double eurvalue;

}
