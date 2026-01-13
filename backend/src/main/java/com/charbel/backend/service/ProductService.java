package com.charbel.backend.service;

import org.springframework.stereotype.Service;

import com.charbel.backend.entity.Product;
import com.charbel.backend.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
