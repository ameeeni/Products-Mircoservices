package com.esoft.productsservice.projections;

import com.esoft.productsservice.entities.Product;
import com.esoft.productsservice.events.ProductCreatedEvent;
import com.esoft.productsservice.repositories.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class ProductEventHandler {
    private final ProductRepository productRepository;

    public ProductEventHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
        @ExceptionHandler(value=Exception.class)
    public void handle(Exception exception) throws Exception {
throw exception;
    }
@ExceptionHandler(value = IllegalArgumentException.class)
public void handle(IllegalArgumentException exception) {

}
    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {
        //after the ProductCreatedEvent was handled after the command of creation
        // we will save what was created in the database

        Product product = new Product();
        product.setId(event.getId());
        product.setTitle(event.getTitle());
        product.setPrice(event.getPrice());
        product.setQuantity(event.getQuantity());
        //Saving the product in the database
        try {
            productRepository.save(product);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        if(true) throw new  Exception("an Error occurred in the CreateProductCommand ");

    }
}
