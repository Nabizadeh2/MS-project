package com.example.customerms.service;

import com.example.customerms.model.CustomerDto;

import java.math.BigDecimal;

public interface CustomerService {
    void createCustomer(CustomerDto customer);

    CustomerDto findById(Long id);

    void updateCustomer(Long id, CustomerDto customerDto);

    void deleteCustomer(Long id);

    void decreaseBalance(Long customerId, BigDecimal price);

}
