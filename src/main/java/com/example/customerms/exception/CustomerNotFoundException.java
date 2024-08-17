package com.example.customerms.exception;

public class CustomerNotFoundException  extends RuntimeException {

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
