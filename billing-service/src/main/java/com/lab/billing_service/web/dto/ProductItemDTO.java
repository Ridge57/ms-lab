package com.lab.billing_service.web.dto;

import com.lab.billing_service.entities.ProductItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductItemDTO {
    private Long id;
    private Long productID;
    private double quantity;
    private double price;
    private double discount;
    private ProductDTO productDTO;

    public static ProductItemDTO fromProductItem(ProductItem productItem) {
        return ProductItemDTO
                .builder()
                .id(productItem.getId())
                .quantity(productItem.getQuantity())
                .discount(productItem.getDiscount())
                .price(productItem.getPrice())
                .productID(productItem.getProductID())
                .productDTO(ProductDTO.fromProduct(productItem.getProduct()))
                .build();
    }
}
