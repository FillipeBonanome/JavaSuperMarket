package com.supermaket.marketapi.controller;

import com.supermaket.marketapi.dtos.ProductDTO;
import com.supermaket.marketapi.entity.Product;
import com.supermaket.marketapi.exception.ProductException;
import com.supermaket.marketapi.repository.ProductRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ProductDTO> registerProduct(@Valid @RequestBody ProductDTO productDTO, UriComponentsBuilder uriBuilder) {
        if (productRepository.existsByCode(productDTO.code())) {
           throw new ProductException("Code already registered");
        }

        if (productRepository.existsByName(productDTO.name())) {
            throw new ProductException("Name already registered");
        }

        Product product = new Product(productDTO);
        productRepository.save(product);

        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getCode()).toUri();

        return ResponseEntity.created(uri).body(productDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> readProduct(@PathVariable Long id) {
        Optional<Product> product = productRepository.findByCode(id);
        if (product.isEmpty()) {
            throw new ProductException("Product not found");
        }

        Product newProduct = product.get();
        return ResponseEntity.status(HttpStatus.OK).body(new ProductDTO(newProduct));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductDTO> editProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        Optional<Product> product = productRepository.findByCode(id);
        if (product.isEmpty()) {
            throw new ProductException("Product not found");
        }

        Product proxy = productRepository.getReferenceById(product.get().getId());
        proxy.updateProduct(productDTO);
        return ResponseEntity.ok().body(new ProductDTO(proxy));
    }

}
