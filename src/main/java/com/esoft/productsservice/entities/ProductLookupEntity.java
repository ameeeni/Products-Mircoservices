package com.esoft.productsservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "products-lookup")
public class ProductLookupEntity implements Serializable {
    @Id

    @GeneratedValue(strategy = GenerationType.UUID)
    private String id ;
    @Column(unique = true)
    private String title;


}
