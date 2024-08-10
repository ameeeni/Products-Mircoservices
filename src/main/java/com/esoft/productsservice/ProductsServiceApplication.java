package com.esoft.productsservice;

import com.esoft.productsservice.core.errorhandling.ProductServiceEventErrorHandler;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
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
	/**
	 * Here i will register the Processing-Group product-group to the EventProcessing
	 * so will build a ProductServiceEventErrorHandler listener to return
	 * the instance of EventProcessingConfigurer configuration
	 * we can use the PropagationErrorHandler Provided by Axon Framework
	 **/
    @Autowired
	public  void configure(EventProcessingConfigurer configuration){
		configuration.
				registerListenerInvocationErrorHandler
						("product-group",
								conf-> new ProductServiceEventErrorHandler());
	}
}
