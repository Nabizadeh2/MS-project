package com.example.productms.service;

import com.example.productms.model.ProductDto;

public interface ProductService {
    void createProduct(ProductDto productDto);

    ProductDto findById(Long id);

    void updateProduct(Long id, ProductDto productDto);
    void increaseCount(Long id, Integer count);

    void deleteProduct(long id);

    void decreaseCount(Long productId, Integer count);
}
