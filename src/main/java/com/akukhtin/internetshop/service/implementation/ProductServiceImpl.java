package com.akukhtin.internetshop.service.implementation;

import com.akukhtin.internetshop.model.Product;
import com.akukhtin.internetshop.repository.ProductRepository;
import com.akukhtin.internetshop.service.ProductService;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public Optional<Product> createProduct(Product product) {
        Product saveProduct = productRepository.save(product);
        return Optional.of(saveProduct);
    }

    @Transactional
    @Override
    public Optional<Product> updateByProduct(Long id, Product product) {
        Product updateProduct = productRepository.saveAndFlush(product);
        return Optional.of(updateProduct);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> getById(Long productId) {
       return productRepository.findById(productId);
    }


}
