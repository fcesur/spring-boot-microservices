package com.fcesur.productservice.service;

import com.fcesur.productservice.dto.ProductResponse;
import com.fcesur.productservice.dto.ProductRequest;
import com.fcesur.productservice.mapper.ProductMapper;
import com.fcesur.productservice.model.Product;
import com.fcesur.productservice.repository.ProductRepository;
import exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductMapper productMapper;

    private final ProductRepository productRepository;


    public ProductResponse save(ProductRequest productRequest) {
        Product product = productMapper.map(productRequest);

        return productMapper.mapToResponse(productRepository.save(product));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse findById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        return productMapper.mapToResponse(product);
    }

    public ProductResponse update(String id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        productMapper.update(product, request);

        Product updatedProduct = productRepository.save(product);

        log.info("Product saved with id: {}", product.getId());

        return productMapper.mapToResponse(updatedProduct);
    }

    public void deleteById(String id) {
        productRepository.deleteById(id);

        log.info("Product deleted with id: {}", id);
    }

}
