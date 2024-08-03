package com.esoft.productsservice.entities;

import com.netflix.discovery.provider.ISerializer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.BeanInfo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Product implements Serializable {
    @Id

    @GeneratedValue(strategy = GenerationType.UUID)
    private String id ;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
