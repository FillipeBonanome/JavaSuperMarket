package com.supermaket.marketapi.entity;

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

}
