package com.supermaket.marketapi.entity;

import com.supermaket.marketapi.UnitType;
import com.supermaket.marketapi.dtos.ProductDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Product")
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    Long code;
    Float unitPrice;

    @Enumerated(EnumType.STRING)
    UnitType unitType;
    Float taxPercent;

    public Product(@Valid ProductDTO productDTO) {
        this.name = productDTO.name();
        this.code = productDTO.code();
        this.unitPrice = productDTO.unitPrice();
        this.unitType = productDTO.unitType();
        this.taxPercent = productDTO.taxPercent();
    }

    //This shouldn't change the product's code
    public void updateProduct(ProductDTO productDTO) {
        this.name = productDTO.name();
        this.unitPrice = productDTO.unitPrice();
        this.unitType = productDTO.unitType();
        this.taxPercent = productDTO.taxPercent();
    }
}
