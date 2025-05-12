package com.supermaket.marketapi.dtos;

import com.supermaket.marketapi.entity.Cart;
import com.supermaket.marketapi.entity.CartItem;
import com.supermaket.marketapi.entity.Product;

public record CartItemDTO(
        CartDTO cartDTO,
        ProductDTO productDTO,
        Long amount,
        Float subtotal
) {
    public CartItemDTO(CartItem cartItem) {
        this(new CartDTO(cartItem.getCart()), new ProductDTO(cartItem.getProduct()), cartItem.getAmount(), cartItem.getSubtotal());
    }
}
