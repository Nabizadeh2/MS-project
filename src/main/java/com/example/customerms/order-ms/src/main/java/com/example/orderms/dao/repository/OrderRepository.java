package com.example.orderms.dao.repository;

import com.example.orderms.dao.entit.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
