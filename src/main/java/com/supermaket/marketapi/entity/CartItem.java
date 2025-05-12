package com.supermaket.marketapi.entity;

import com.supermaket.marketapi.dtos.CartItemDTO;
import com.supermaket.marketapi.exception.ProductException;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "CartItem")
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product product;
    Long amount;
    Float subtotal;

    public CartItem(CartItemDTO cartItemDTO) {
        this.cart = new Cart(cartItemDTO.cartDTO());
        this.product = new Product(cartItemDTO.productDTO());
        this.amount = cartItemDTO.amount();
        this.subtotal = cartItemDTO.subtotal();
    }
}
