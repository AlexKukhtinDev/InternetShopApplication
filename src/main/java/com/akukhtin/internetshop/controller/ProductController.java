package com.akukhtin.internetshop.controller;

import com.akukhtin.internetshop.exception.ProductNotFoundException;
import com.akukhtin.internetshop.model.Product;
import com.akukhtin.internetshop.repository.ProductRepository;
import com.akukhtin.internetshop.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductRepository productRepository;
    private ProductService productService;

    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping("/perItem")
    public Product perItem(@RequestBody Long productId) {
        Product product = productRepository.getOne(productId);
        return productRepository.saveAndFlush(product);
    }

    @PostMapping("/add")
    public Product add(@RequestBody Product product) {

        productService.createProduct(product);
        return product;
    }

    @PutMapping("/{userId}")
    public Product update(@PathVariable("userId") Long productId, @RequestBody Product product)
            throws ProductNotFoundException {
        return productService.updateByProduct(productId,product).orElseThrow(
                () -> new ProductNotFoundException("Product with id " + productId + "not found.")
        );
    }


}
