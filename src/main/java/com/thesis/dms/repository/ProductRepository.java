package com.thesis.dms.repository;

import com.thesis.dms.entity.product.ProductEntity;

import java.util.List;

public interface ProductRepository {
    void updateListProduct(List<ProductEntity> productEntities);
}
