package com.supermaket.marketapi.dtos;

import com.supermaket.marketapi.entity.Payment;

public record PaymentDTO(
        String name,
        Double value,
        CartDTO cartDTO
) {
    public PaymentDTO(Payment payment) {
        this(payment.getName(), payment.getValue(), new CartDTO(payment.getCart()));
    }
}
