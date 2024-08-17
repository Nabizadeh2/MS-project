package com.example.orderms.mapper;

import com.example.orderms.dao.entit.OrderEntity;
import com.example.orderms.model.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    OrderDto toDto(OrderEntity orderEntity);
    OrderEntity toEntity(OrderDto orderDto);
}
