package com.lab.billing_service.web.dto;

import com.lab.billing_service.entities.Bill;
import com.lab.billing_service.entities.ProductItem;
import com.lab.billing_service.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private Long id;
    private Date date;
    private Long customerID;

    List<ProductItemDTO> productItemDTOs;

    private CustomerDTO customerDTO;

    public static BillDTO buildBillDTO(Bill bill, Customer customer, List<ProductItem> productItems) {
        return BillDTO.builder()
                .id(bill.getId())
                .date(bill.getDate())
                .customerID(bill.getCustomerID())
                .productItemDTOs(buildProductItemDTOs(productItems))
                .customerDTO(CustomerDTO.fromCustomer(customer))
                .build();
    }

    private static List<ProductItemDTO> buildProductItemDTOs(List<ProductItem> productItems) {
        return productItems
                .stream()
                .map(ProductItemDTO::fromProductItem)
                .toList();
    }


}

