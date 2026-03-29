package com.lab.billing_service.web.dto;

import com.lab.billing_service.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    public static CustomerDTO fromCustomer(Customer customer) {
        return CustomerDTO
                .builder()
                .name(customer.getName())
                .id(customer.getId())
                .email(customer.getEmail())
                .build();
    }
}
