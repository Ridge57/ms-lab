package com.lab.billing_service.web;

import com.lab.billing_service.service.BillService;
import com.lab.billing_service.web.dto.BillDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BillingController {
    BillService billService;
    public BillingController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/bills/{id}")
    public ResponseEntity<BillDTO> findBillById(@PathVariable Long id) {
        Long start = System.nanoTime();
        ResponseEntity<BillDTO> res = billService.findBillById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
        Long end = System.nanoTime();
        System.out.println("TEMPS DE TRAVERSEE ===> "+(end-start)/1_000_000+" ms");
        return res;
    }

    @GetMapping("/test")
    public String test() {
        return "ok";
    }
}
