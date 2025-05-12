package com.supermaket.marketapi.controller;

import com.supermaket.marketapi.dtos.CartDTO;
import com.supermaket.marketapi.entity.Cart;
import com.supermaket.marketapi.exception.CartException;
import com.supermaket.marketapi.repository.CartRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("carts")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isEmpty()) {
            throw new CartException("Cart not found");
        }

        Cart newCart = cart.get();
        return ResponseEntity.ok(new CartDTO(newCart));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CartDTO> registerCart(@Valid @RequestBody CartDTO cartDTO, UriComponentsBuilder uriComponentsBuilder) {
        Cart cart = new Cart(cartDTO);
        cartRepository.save(cart);

        var uri = uriComponentsBuilder.path("/cart/{ìd}").buildAndExpand(cart.getId()).toUri();

        return ResponseEntity.created(uri).body(cartDTO);
    }

}
