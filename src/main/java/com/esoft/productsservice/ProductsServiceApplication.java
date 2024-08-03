package com.esoft.productsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient //enable the discovery client annot to make api gatway find the product routes from eureka discovry
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.esoft.productsservice.repositories")

public class ProductsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsServiceApplication.class, args);
	}

}
