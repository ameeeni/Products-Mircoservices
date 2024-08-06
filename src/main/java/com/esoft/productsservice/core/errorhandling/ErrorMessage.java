package com.esoft.productsservice.core.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
public class ErrorMessage {
    private final Date timestamp;
    private   final String message;
}
