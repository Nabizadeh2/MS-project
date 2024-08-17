package com.example.orderms.service.impl;

import com.example.orderms.dao.entit.OrderEntity;
import com.example.orderms.dao.repository.OrderRepository;
import com.example.orderms.exception.CustomerNotFoundException;
import com.example.orderms.feign.CustomerFeign;
import com.example.orderms.feign.ProductFeign;
import com.example.orderms.mapper.OrderMapper;
import com.example.orderms.model.OrderDto;
import com.example.orderms.service.OrdersService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrdersService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final CustomerFeign customerFeign;
    private final ProductFeign productFeign;

    @Override
    @Transactional
    public void createOrder(OrderDto orderDto) {
        BigDecimal multiply = orderDto.getPrice().multiply(BigDecimal.valueOf(orderDto.getCount()));
        customerFeign.decreaseBalance(orderDto.getCustomerId(), multiply);
        productFeign.decreaseCount(orderDto.getProductId(), orderDto.getCount());
        orderRepository.save(orderMapper.toEntity(orderDto));
    }

    @Override
    public OrderDto findById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toDto)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id " + id));
    }

    @Override
    public List<OrderDto> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public void updateOrder(Long id, OrderDto orderDto) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id " + id));

        orderEntity.setName(orderDto.getName());
        orderEntity.setPrice(orderDto.getPrice());
        orderEntity.setCount(orderEntity.getCount());
        orderRepository.save(orderEntity);
    }


    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
