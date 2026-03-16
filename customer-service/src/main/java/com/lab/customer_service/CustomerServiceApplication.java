package com.lab.customer_service;

import com.lab.customer_service.entities.Customer;
import com.lab.customer_service.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(CustomerRepository customerRepository) {
		return args -> {
			customerRepository.save(Customer.builder().name("Ridge").email("ridge@gmail.com").build());
			customerRepository.save(Customer.builder().name("Sara").email("sara@gmail.com").build());
			customerRepository.save(Customer.builder().name("Gabrielle").email("gabrielle@gmail.com").build());
		};
	}

}
