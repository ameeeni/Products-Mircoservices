package com.esoft.productsservice.query.rest;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestModel {
    private String id ;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
