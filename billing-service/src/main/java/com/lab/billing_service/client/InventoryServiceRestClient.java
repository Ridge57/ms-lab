package com.lab.billing_service.client;

import com.lab.billing_service.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface InventoryServiceRestClient {
    @GetMapping(value = "/products/{id}",produces = "application/json")
    Product findProductrById(@PathVariable Long id);
}
