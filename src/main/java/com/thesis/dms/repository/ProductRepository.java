package com.thesis.dms.repository;

import com.thesis.dms.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository {
    void updateListProduct();
}
