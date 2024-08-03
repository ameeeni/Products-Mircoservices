package com.esoft.productsservice.command.commands;

import com.esoft.productsservice.events.ProductCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@AllArgsConstructor
@NoArgsConstructor
public class ProductAggregate {
    @AggregateIdentifier
    private String id ;
    private String title;
    private BigDecimal price;
    private Integer quantity;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand){
     //Validate Create Command
        /**here we are validating the product items whenever the product is with
         * a valid data we will send the command to the Command Bus however we will
         * also handle apply the event of the creation to store it in the event store **/

        if(createProductCommand.getPrice().compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        if(createProductCommand.getTitle().isBlank() || createProductCommand.getTitle()==null){
            throw new IllegalArgumentException("Title is blank");
        }
        //apply to store the event in the event Store
        AggregateLifecycle.apply(
                new ProductCreatedEvent(createProductCommand.getId(),
                        createProductCommand.getTitle(),
                        createProductCommand.getPrice(),createProductCommand.getQuantity()));


    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent){
        this.id=productCreatedEvent.getId();
        this.title=productCreatedEvent.getTitle();
        this.price=productCreatedEvent.getPrice();
        this.quantity=productCreatedEvent.getQuantity();

    }
}
