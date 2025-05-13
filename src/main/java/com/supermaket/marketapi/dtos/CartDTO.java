package com.supermaket.marketapi.dtos;

import com.supermaket.marketapi.CartStatus;
import com.supermaket.marketapi.entity.Cart;
import com.supermaket.marketapi.entity.CartPayment;
import com.supermaket.marketapi.entity.Payment;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CartDTO(
        List<CartItemDTO> cartItemDTOList,
        List<CartPaymentDTO> paymentDTOList,
        CartStatus status
) {
    public CartDTO(Cart newCart) {
        this(
                newCart.getCartItems().stream().map(CartItemDTO::new).toList(),
                newCart.getPayments().stream().map(p -> new CartPaymentDTO(p)).toList(),
                newCart.getStatus()
        );
    }
}
