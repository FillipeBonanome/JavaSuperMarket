package com.supermaket.marketapi.controller;

import com.supermaket.marketapi.dtos.ProductDTO;
import com.supermaket.marketapi.entity.Product;
import com.supermaket.marketapi.repository.ProductRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<String> registerProduct(@Valid @RequestBody ProductDTO productDTO) {
        if (productRepository.existsByCode(productDTO.code())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Code already registered");
        }

        if (productRepository.existsByName(productDTO.name())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Name already registered");
        }

        Product product = new Product(productDTO);
        productRepository.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body("Products registered");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readProduct(@PathVariable Long id) {
        Optional<Product> product = productRepository.findByCode(id);
        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        Product newProduct = product.get();
        return ResponseEntity.status(HttpStatus.OK).body(newProduct);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> editProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        var proxy = productRepository.getReferenceById(id);
        proxy.updateProduct(productDTO);
        return ResponseEntity.ok().body(proxy);
    }

}
