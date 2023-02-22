package com.thesis.dms.repository.custom;

import com.thesis.dms.entity.product.ProductEntity;

import java.util.List;

public interface CustomProductRepository {
    void updateListProduct(List<ProductEntity> productEntities);

}
