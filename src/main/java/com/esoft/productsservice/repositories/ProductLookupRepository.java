package com.esoft.productsservice.repositories;

import com.esoft.productsservice.entities.ProductLookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductLookupRepository  extends JpaRepository<ProductLookupEntity, String> {
    ProductLookupEntity findProductByIdAndTitle(String id, String title);
}
