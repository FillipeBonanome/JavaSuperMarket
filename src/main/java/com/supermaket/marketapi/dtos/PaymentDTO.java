package com.supermaket.marketapi.dtos;

import com.supermaket.marketapi.entity.Payment;

public record PaymentDTO(
        String name,
        CartDTO cartDTO
) {
    public PaymentDTO(Payment payment) {
        this(payment.getName(), new CartDTO(payment.getCart()));
    }
}
