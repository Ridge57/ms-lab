package com.lab.inventory_service;

import com.lab.inventory_service.entities.Product;
import com.lab.inventory_service.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(ProductRepository productRepository) {
		return args -> {
			productRepository.save(Product.builder().name("Computer")
					.price(500)
					.quantity(100)
					.build()
			);
			productRepository.save(Product.builder().name("Printer")
					.price(70)
					.quantity(10)
					.build()
			);
			productRepository.save(Product.builder().name("Smartphone")
					.price(1200)
					.quantity(400)
					.build()
			);
		};
	}

}
