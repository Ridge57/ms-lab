package com.lab.billing_service.web;

import com.lab.billing_service.client.CustomerServiceRestClient;
import com.lab.billing_service.client.InventoryServiceRestClient;
import com.lab.billing_service.entities.Bill;
import com.lab.billing_service.entities.ProductItem;
import com.lab.billing_service.model.Customer;
import com.lab.billing_service.repository.BillRepository;
import com.lab.billing_service.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BillingController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerServiceRestClient customerServiceRestClient;
    @Autowired
    private InventoryServiceRestClient inventoryServiceRestClient;

    @GetMapping("/bills/{id}")
    public Bill findBillById(@PathVariable Long id) {
        return billRepository.findById(id).map(bill -> {
            Customer customer = customerServiceRestClient.findCustomerById(bill.getCustomerID());
            bill.setCustomer(customer);
            return bill;
        }).orElse(null);
    }
}
