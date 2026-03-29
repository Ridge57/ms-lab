package com.lab.billing_service.service;

import com.lab.billing_service.client.CustomerServiceRestClient;
import com.lab.billing_service.client.InventoryServiceRestClient;
import com.lab.billing_service.entities.Bill;
import com.lab.billing_service.entities.ProductItem;
import com.lab.billing_service.model.Customer;
import com.lab.billing_service.model.Product;
import com.lab.billing_service.repository.BillRepository;
import com.lab.billing_service.repository.ProductItemRepository;
import com.lab.billing_service.web.dto.BillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.lab.billing_service.web.dto.BillDTO.buildBillDTO;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerServiceRestClient customerServiceRestClient;
    @Autowired
    private InventoryServiceRestClient inventoryServiceRestClient;

    public Optional<BillDTO> findBillById(Long id) {
        return billRepository.findById(id).map(this::enrichBillAsync);
    }

    private BillDTO enrichBill(Bill bill) {
        Customer customer = customerServiceRestClient.findCustomerById(bill.getCustomerID());
        List<ProductItem> completeProductItems = bill.getProductItems().stream().peek(productItem -> {
            Product product = inventoryServiceRestClient.findProductById(productItem.getProductID());
            productItem.setProduct(product);
        }).toList();
        return buildBillDTO(bill,customer,completeProductItems);
    }

    private BillDTO enrichBillAsync(Bill bill) {
        CompletableFuture<Customer> customerFuture = CompletableFuture
                .supplyAsync(()->customerServiceRestClient.findCustomerById(bill.getCustomerID()));

        CompletableFuture<List<ProductItem>> productItemsFuture = CompletableFuture.supplyAsync(()->{
             return bill.getProductItems()
                    .stream()
                    .peek(productItem -> {
                        Product product = inventoryServiceRestClient.findProductById(productItem.getProductID());
                        productItem.setProduct(product);
                        })
                     .toList();
        });

        /*List<CompletableFuture<ProductItem>> completeProductItemsFuture = bill.getProductItems().stream().map(productItem -> CompletableFuture.supplyAsync(()->{
            Product product = inventoryServiceRestClient.findProductById(productItem.getProductID());
            productItem.setProduct(product);
            return productItem;
        })).toList();

        List<ProductItem> completeProductItems = completeProductItemsFuture.stream()
                .map(CompletableFuture::join)
                .toList();
         */
        Customer customer = customerFuture.join();
        List<ProductItem> completeProductItems = productItemsFuture.join();
        return buildBillDTO(bill,customer,completeProductItems);
    }
}
