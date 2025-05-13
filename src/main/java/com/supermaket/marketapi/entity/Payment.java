package com.supermaket.marketapi.entity;

import com.supermaket.marketapi.dtos.PaymentDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    Cart cart;

    public Payment(PaymentDTO paymentDTO) {
        this.name = paymentDTO.name();
        this.cart = new Cart(paymentDTO.cartDTO());
    }
}
