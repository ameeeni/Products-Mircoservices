package com.esoft.productsservice.command.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.UUID;
@Builder
@Data
public class CreateProductCommand {
    @TargetAggregateIdentifier
    private final String id ;
    private final String title;
    private final BigDecimal price;
    private final Integer quantity;
}
