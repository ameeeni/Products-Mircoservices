package com.esoft.productsservice.command.interceptors;

import com.esoft.productsservice.command.commands.CreateProductCommand;
import com.esoft.productsservice.entities.ProductLookupEntity;
import com.esoft.productsservice.repositories.ProductLookupRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

import static org.hibernate.type.descriptor.JdbcExtractingLogging.LOGGER;

@Component
@Slf4j
@AllArgsConstructor
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

private final ProductLookupRepository productLookupRepository;
    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>,
            CommandMessage<?>> handle
            (@Nonnull List<? extends CommandMessage<?>> messages) {
        return (index , command)->{
            log.info("Intercepted command:" + command.getPayloadType());

            if(CreateProductCommand.class.equals(command.getPayloadType())) {
               CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();
               ProductLookupEntity productLookupEntity =productLookupRepository.findProductByIdAndTitle(((CreateProductCommand) command.getPayload()).getId(), ((CreateProductCommand) command.getPayload()).getTitle());
               if(Objects.nonNull(productLookupEntity)){
                   throw new IllegalStateException("Product already exists with id:" + productLookupEntity.getId());
               }
           }
            return command;
        };
    }
}
