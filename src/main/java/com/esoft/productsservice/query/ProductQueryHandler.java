package com.esoft.productsservice.query;

import com.esoft.productsservice.entities.Product;
import com.esoft.productsservice.query.queries.FindProductsQuery;
import com.esoft.productsservice.query.rest.ProductRequestModel;
import com.esoft.productsservice.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ProductQueryHandler {
private final ProductRepository productRepository;
@QueryHandler
public List<ProductRequestModel> getProducts(FindProductsQuery query) {
    List<ProductRequestModel> productRequestModels = new ArrayList<>();
    List<Product> products= productRepository.findAll();
    ProductRequestModel productRequestModel = new ProductRequestModel();
    products.stream().forEach(product ->
    {
        BeanUtils.copyProperties(product,productRequestModel);
        productRequestModels.add(productRequestModel);
    });

    return productRequestModels;
}
}
