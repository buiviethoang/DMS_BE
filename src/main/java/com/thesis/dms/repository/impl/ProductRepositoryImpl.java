package com.thesis.dms.repository.impl;

import com.thesis.dms.entity.product.ProductEntity;
import com.thesis.dms.repository.ProductRepository;
import com.thesis.dms.service.EntityManagerService;
import org.springframework.data.jpa.repository.Query;

public class ProductRepositoryImpl implements ProductRepository {

    private EntityManagerService entityManagerService;
    @Override
    public void updateListProduct() {
        String queryString = "select * from product";
        Query query = entityManagerService.getEntityManager().createNativeQuery(queryString, ProductEntity.class);
    }
}
