package com.supermaket.marketapi.dtos;

import com.supermaket.marketapi.entity.Payment;

public record PaymentDTO(
        String name
) {
    public PaymentDTO(Payment payment) {
        this(payment.getName());
    }
}
