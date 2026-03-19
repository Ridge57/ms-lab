package com.lab.billing_service.entities;

import com.lab.billing_service.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bill {
    @Id @GeneratedValue
    private Long id;
    private Date date;
    private Long customerID;

    @OneToMany(mappedBy = "bill")
    List<ProductItem> productItems;

    @Transient
    private Customer customer;
}
