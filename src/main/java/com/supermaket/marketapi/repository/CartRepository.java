package com.supermaket.marketapi.repository;

import com.supermaket.marketapi.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
