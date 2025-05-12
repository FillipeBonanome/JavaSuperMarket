package com.supermaket.marketapi.entity;

import com.supermaket.marketapi.CartStatus;
import com.supermaket.marketapi.dtos.CartDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //@NotEmpty(message = "Your cart can't be empty")
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    List<CartItem> cartItems;

    //@NotEmpty(message = "Your cart need at least one method of payment")
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    List<Payment> payments;

    @Enumerated(EnumType.STRING)
    CartStatus status;

    public Cart(@Valid CartDTO cartDTO) {
        this.cartItems = cartDTO.cartItemDTOList().stream().map(CartItem::new).toList();
        this.payments = cartDTO.paymentDTOList().stream().map(Payment::new).toList();
        this.status = cartDTO.status();
    }
}
