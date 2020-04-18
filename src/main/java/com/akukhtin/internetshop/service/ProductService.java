package com.akukhtin.internetshop.service;

import com.akukhtin.internetshop.model.Product;
import java.util.Optional;

public interface ProductService {
    Optional<Product> createProduct(Product product);
    Optional<Product> updateByProduct(Long id,Product product);
    Optional<Product> getById(Long productId);

}
