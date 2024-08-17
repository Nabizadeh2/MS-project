package com.example.productms.exception;

public class ProductNotFound extends RuntimeException{
    public ProductNotFound(String s) {
        super(s);
    }
}
