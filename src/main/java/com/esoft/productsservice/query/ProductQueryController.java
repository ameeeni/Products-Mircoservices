package com.esoft.productsservice.query;

import com.esoft.productsservice.query.queries.FindProductsQuery;
import com.esoft.productsservice.query.rest.ProductRequestModel;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/products")
public class ProductQueryController {
    @Autowired
    QueryGateway queryGateway;

//    @GetMapping
//    public List<ProductRequestModel> getProducts(){
//     FindProductsQuery findProductsQuery =new   FindProductsQuery();
//      List<ProductRequestModel> products=  queryGateway.query
//              (findProductsQuery, ResponseTypes.multipleInstancesOf(ProductRequestModel.class)).join();
//        return products;
//    }
@GetMapping
public List<ProductRequestModel> getProducts() {
    FindProductsQuery findProductsQuery = new FindProductsQuery();
    CompletableFuture<List<ProductRequestModel>> futureProducts = queryGateway.query(
            findProductsQuery,
            ResponseTypes.multipleInstancesOf(ProductRequestModel.class)
    );

    // Handle exceptions and provide an alternative result
    futureProducts = futureProducts.exceptionally(ex -> {
        System.err.println("Exception: " + ex.getMessage());
        return List.of(); // Return an empty list or handle as necessary
    });

    return futureProducts.join(); // Wait for the future to complete and get the result
}
}
