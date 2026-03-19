package com.lab.billing_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private double price;
    private int quantity;
}
