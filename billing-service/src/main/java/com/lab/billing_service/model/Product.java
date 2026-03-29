package com.lab.billing_service.model;

import lombok.*;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Product {
    private Long id;
    private String name;
    private double price;
    private int quantity;
}
