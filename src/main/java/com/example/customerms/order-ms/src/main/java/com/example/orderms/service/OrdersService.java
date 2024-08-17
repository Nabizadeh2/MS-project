package com.example.orderms.service;

import com.example.orderms.model.OrderDto;

import java.util.List;

public interface OrdersService {
    void createOrder(OrderDto orderDto);
    OrderDto findById(Long id);
    List<OrderDto> findAll();
    void updateOrder(Long id,OrderDto orderDto);
    void deleteOrder(Long id);
}
