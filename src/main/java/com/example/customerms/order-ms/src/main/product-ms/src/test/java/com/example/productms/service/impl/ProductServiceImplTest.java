package com.example.productms.service.impl;

import com.example.productms.dao.entity.ProductEntity;
import com.example.productms.dao.repository.ProductRepository;
import com.example.productms.exception.ProductNotFound;
import com.example.productms.mapper.ProductMapper;
import com.example.productms.model.ProductDto;
import com.example.productms.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
     @Mock
     private  ProductMapper productMapper;

     @Mock
     private  ProductRepository productRepository;

     @InjectMocks
     private ProductServiceImpl productService;

     @Test
    public void find_by_id_product_success_case(){
         Long productId=1L;

         ProductDto productDto=ProductDto
                 .builder()
                 .name("Test")
                 .price(BigDecimal.valueOf(12000))
                 .count(3)
                 .build();

          ProductEntity productEntity=ProductEntity
                  .builder()
                  .name(productDto.getName())
                  .price(productDto.getPrice())
                  .count(productDto.getCount())
                  .build();
          when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));
          when(productMapper.toDto(productEntity)).thenReturn(productDto);

          Assertions.assertEquals(productEntity.getId(),productDto.getId());
          Assertions.assertEquals(productEntity.getName(),productDto.getName());
          Assertions.assertEquals(productEntity.getPrice(),productDto.getPrice());
          Assertions.assertEquals(productEntity.getCount(),productDto.getCount());

          Assertions.assertDoesNotThrow(()->productService.findById(productId));
     }

     @Test
     public void find_by_id_customer_failed_case() {
         Long productId = 1L;
         when(productRepository.findById(productId)).thenReturn(Optional.empty());

         Assertions.assertThrows(ProductNotFound.class, () -> productService.findById(productId));
     }


    @Test
    public void create_customer_success_case() {
        ProductDto productDto = ProductDto
                .builder()
                .name("Test Test")
                .price(BigDecimal.valueOf(222))
                .count(5)
                .build();

        ProductEntity productEntity = ProductEntity.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .count(productDto.getCount())
                .build();

        when(productRepository.save(productEntity)).thenReturn(productEntity);
        when(productMapper.toEntity(productDto)).thenReturn(productEntity);

        Assertions.assertDoesNotThrow(() -> productService.createProduct(productDto));
    }
}