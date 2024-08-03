package com.esoft.productsservice.projections;

import com.esoft.productsservice.entities.ProductLookupEntity;
import com.esoft.productsservice.events.ProductCreatedEvent;
import com.esoft.productsservice.repositories.ProductLookupRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ProcessingGroup("product-group")
public class ProductLookupEventHandler {
    private final ProductLookupRepository productLookupRepository;
    //Saving the Product for Lookup to check in if there is any Product is presented in
    //the data base
    @EventHandler
    public void handle(ProductCreatedEvent event) {
        ProductLookupEntity productLookupEntity
        =ProductLookupEntity
                .builder()
                .id(event.getId())
                .title(event.getTitle())
                .build();
        productLookupRepository.save(productLookupEntity);
    }
}
