package com.lab.billing_service.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lab.billing_service.model.Customer;
import com.lab.billing_service.model.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class ProductItem {
    @Id @GeneratedValue
    private Long id;
    private Long productID;
    private double quantity;
    private double price;
    private double discount;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Bill bill;
    @Transient
    private Product product;
}
