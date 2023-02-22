package com.thesis.dms.repository;

import com.thesis.dms.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
//    @Query("insert into product (code, name, unit_quantity, status, color, mass_no, sku, image, quantity) values ")
//    ProductEntity saveProduct(
//            @Param("code") String code,
//            @Param("name") String name,
//            @Param("unit_quantity") String unit_quantity,
//            @Param("status") String status,
//            @Param("color") String color,
//            @Param("mass_no") String mass_no,
//            @Param("sku") String sku,
//            @Param("image") String image,
//            @Param("quantity") String quantity
//            );
}
