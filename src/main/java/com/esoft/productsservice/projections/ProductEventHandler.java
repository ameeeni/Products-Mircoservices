package com.esoft.productsservice.projections;

import com.esoft.productsservice.entities.Product;
import com.esoft.productsservice.events.ProductCreatedEvent;
import com.esoft.productsservice.repositories.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventHandler {
    private final ProductRepository productRepository;

    public ProductEventHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @EventHandler
    public void on(ProductCreatedEvent event) {
        //after the ProductCreatedEvent was handled after the command of creation
        // we will save what was created in the database

        Product product = new Product();
        product.setId(event.getId());
        product.setTitle(event.getTitle());
        product.setPrice(event.getPrice());
        product.setQuantity(event.getQuantity());
        //Saving the product in the database
        productRepository.save(product);
    }
}
