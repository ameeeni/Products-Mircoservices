package com.esoft.productsservice.core.errorhandling;

import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ProductServiceErrorHandler {
    /*** Centralizing the Error Handling for the whole Project using the annotation of @Controller Advice**/
@ExceptionHandler(value = {IllegalStateException.class})
    private ResponseEntity<Object> handleIllegalStateException(final IllegalStateException e) {
    /**Customizing the Error Message**/
    ErrorMessage errorMessage =
            ErrorMessage
                    .builder()
                    .timestamp(new Date())
                    .message(e.getMessage())
                    .build();
    return new ResponseEntity<>(errorMessage, new HttpHeaders() , HttpStatus.INTERNAL_SERVER_ERROR);
}
    @ExceptionHandler(value = {Exception.class})
    private ResponseEntity<Object> handleOtherException(final Exception e) {
        ErrorMessage errorMessage =
                ErrorMessage
                        .builder()
                        .timestamp(new Date())
                        .message(e.getLocalizedMessage())
                        .build();
        return new ResponseEntity<>(errorMessage, new HttpHeaders() , HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = {CommandExecutionException.class})
    private ResponseEntity<Object> handleCommandExecutionException(final CommandExecutionException e) {
        ErrorMessage errorMessage =
                ErrorMessage
                        .builder()
                        .timestamp(new Date())
                        .message(e.getLocalizedMessage())
                        .build();
        return new ResponseEntity<>(errorMessage, new HttpHeaders() , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
