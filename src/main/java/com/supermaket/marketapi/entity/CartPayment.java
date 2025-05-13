package com.supermaket.marketapi.entity;

import com.supermaket.marketapi.dtos.CartPaymentDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CartPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    Payment payment;
    Double value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    Cart cart;

    public CartPayment(CartPaymentDTO cartPaymentDTO) {
        this.payment = new Payment(cartPaymentDTO.paymentDTO());
        this.value = cartPaymentDTO.value();
        this.cart = new Cart(cartPaymentDTO.cartDTO());
    }
}
