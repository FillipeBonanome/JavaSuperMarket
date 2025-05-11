package com.supermaket.marketapi.repository;

import com.supermaket.marketapi.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByCode(@NotNull(message = "Product code must not be null") Long code);
    boolean existsByName(@NotBlank(message = "Product name must not be null") String name);
    Optional<Product> findByCode(Long id);
    Object getReferenceByCode(Long id);
}
