package com.example.productms.mapper;

import com.example.productms.dao.entity.ProductEntity;
import com.example.productms.model.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    ProductDto toDto(ProductEntity productEntity);

    ProductEntity toEntity(ProductDto productDto);
}
