package com.example.customerms.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerDto {
    private Long id;
    private String fullName;
    private Integer age;
    private String pin;
    private BigDecimal balance;

}
