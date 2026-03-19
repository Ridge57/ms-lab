package com.lab.billing_service.repository;

import com.lab.billing_service.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductItemRepository extends JpaRepository<ProductItem,Long> {
    List<ProductItem> findByBillId(Long billId);
}
