package com.example.productms.service.impl;

import com.example.productms.dao.entity.ProductEntity;
import com.example.productms.dao.repository.ProductRepository;
import com.example.productms.exception.ProductNotFound;
import com.example.productms.mapper.ProductMapper;
import com.example.productms.model.ProductDto;
import com.example.productms.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void createProduct(ProductDto productDto) {
        productRepository.save(productMapper.toEntity(productDto));
    }

    @Override
    public ProductDto findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new ProductNotFound("product not found with id" + id));
    }

    @Override
    @Transactional
    public void updateProduct(Long id, ProductDto productDto) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound("product not found with id" + id));

        productEntity.setCount(productEntity.getCount());
        productEntity.setName(productEntity.getName());
        productEntity.setPrice(productEntity.getPrice());

        productRepository.save(productEntity);
    }

    @Override
    public void increaseCount(Long id, Integer count) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound("product not found with id" + id));

        productEntity.setCount(productEntity.getCount() + count);
        productRepository.save(productEntity);
    }

    @Override
    @Transactional
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void decreaseCount(Long productId, Integer count) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFound("product not found with id" + productId));

        productEntity.setCount(productEntity.getCount() - count);
        productRepository.save(productEntity);
    }




}
