package com.lab.billing_service.model;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Customer {
    private Long id;
    private String name;
    private String email;
}
