package com.lab.customer_service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Customer {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String email;
}
