package com.supermaket.marketapi.dtos;

import com.supermaket.marketapi.entity.CartItem;
import com.supermaket.marketapi.entity.Product;

public record CartItemDTO(
        Long cartId,
        ProductDTO productDTO,
        Long amount,
        Float subtotal
) {
    public CartItemDTO(CartItem cartItem) {
        this(cartItem.getCart().getId(), new ProductDTO(cartItem.getProduct()), cartItem.getAmount(), cartItem.getSubtotal());
    }
}
