package com.supermaket.marketapi.dtos;

import com.supermaket.marketapi.entity.CartPayment;

public record CartPaymentDTO(
        Long id,
        PaymentDTO paymentDTO,
        Double value,
        CartDTO cartDTO
) {
    public CartPaymentDTO(CartPayment cartPayment) {
        this(cartPayment.getId(), new PaymentDTO(cartPayment.getPayment()), cartPayment.getValue(), new CartDTO(cartPayment.getCart()));
    }
}
