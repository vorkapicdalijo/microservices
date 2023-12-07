package com.programmingtechie.productservice;

import com.programmingtechie.productservice.model.Product;
import com.programmingtechie.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableEurekaClient
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner loadData(ProductRepository productRepository) {
		return args -> {
			Product product = Product.builder()
					.name("Iphone 13")
					.description("iphone 13")
					.price(BigDecimal.valueOf(1200))
					.build();

			productRepository.save(product);
		};
	}*/
}
