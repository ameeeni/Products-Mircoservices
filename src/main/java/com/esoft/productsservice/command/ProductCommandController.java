package com.esoft.productsservice.command;

import com.esoft.productsservice.command.commands.CreateProductCommand;
import com.esoft.productsservice.command.rest.CreatePoductRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/products")
public class ProductCommandController {
    private static final Logger log = LoggerFactory.getLogger(ProductCommandController.class);
    /**here we add this variable to get http request and we will observe how
     api gateway will balance between the the two instances of products microservice*/
    @Autowired
    private Environment env;
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public CompletableFuture<String> createProduct(@RequestBody CreatePoductRequestModel createPoductRequestModel) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .title(createPoductRequestModel.getTitle())
                .price(createPoductRequestModel.getPrice())
                .quantity(createPoductRequestModel.getQuantity())
                .id(UUID.randomUUID().toString())
                .build();
        return commandGateway.send(createProductCommand);
    }
//    @GetMapping
//    public String getProduct(){
//        return "Http get Handled";
//    }
//    @PutMapping
//    public String updateProduct(){
//        return "Http put Handled";
//    }
//    @DeleteMapping
//    public String deleteProduct(){
//        return "Http delete Handled";
//    }

}
