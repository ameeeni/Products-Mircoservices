package com.esoft.productsservice.command.rest;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreatePoductRequestModel {
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
