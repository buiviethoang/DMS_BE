package com.thesis.dms.repository;

import com.thesis.dms.entity.product.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query(value = "select cast(json_build_object('code',code, 'des', description, 'name', name, 'sku', sku, 'status', status, 'box_unit', unit_quantity, 'color', color," +
            "            'image_url', image, 'mass_no', mass_no, 'quantity', quantity) as varchar) from product" +
            " where (code like concat('%', :code, '%'))" +
            " and (name like concat('%', :name, '%'))" +
            " and (color like concat('%', :color, '%'))" +
            " and (sku like concat('%', :sku, '%'))" +
            " and (status = :status) ", countQuery = "select count(p) from product p", nativeQuery = true)
    Page<String> search(Pageable pageable,
                       @Param("code") String code,
                        @Param("name") String name,
                        @Param("status") Boolean status,
                        @Param("color") String color,
                        @Param("sku") String sku
                          );

    @Query(value = "select * from product p where p.id = :id limit 1", nativeQuery = true)
    ProductEntity findProductById(@Param("id") Long id);
}
