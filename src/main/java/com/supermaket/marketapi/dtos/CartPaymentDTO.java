package com.supermaket.marketapi.dtos;

public record CartPaymentDTO(
        Long id,
        PaymentDTO paymentDTO,
        Double value
) {
}
