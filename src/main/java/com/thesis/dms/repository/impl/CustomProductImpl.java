package com.thesis.dms.repository.impl;

import com.thesis.dms.entity.BaseEntity;
import com.thesis.dms.entity.product.ProductEntity;
import com.thesis.dms.repository.custom.CustomProductRepository;
import com.thesis.dms.service.EntityManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;

@Repository
@Transactional
public class CustomProductImpl extends BaseEntity implements CustomProductRepository {
    @Autowired
    private EntityManagerService entityManagerService;
    @Override
    public void updateListProduct(List<ProductEntity> productEntities) {
        String queryString = "insert into product (created_date, updated_date, uid, code, name, unit_quantity, status, color, mass_no, sku, image, quantity) values ";
        int batchSize = 100;
        StringBuilder productBuilder = new StringBuilder();

        if (productEntities.isEmpty()) {
            return;
        }
        for (int i = 0; i<productEntities.size(); i++) {
            productBuilder.append("('")
                    .append(Timestamp.valueOf(productEntities.get(i).getCreatedDate().toLocalDateTime())).append("', '")
                    .append(Timestamp.valueOf(productEntities.get(i).getUpdatedDate().toLocalDateTime())).append("', '")
                    .append(productEntities.get(i).getUid()).append("', '")
                    .append(productEntities.get(i).getCode()).append("', '")
                    .append(productEntities.get(i).getName()).append("', '")
                    .append(productEntities.get(i).getUnitQuantity()).append("', '")
                    .append(productEntities.get(i).getStatus()).append("', '")
                    .append(productEntities.get(i).getColor()).append("', '")
                    .append(productEntities.get(i).getMassNumber()).append("', '")
                    .append(productEntities.get(i).getSku()).append("', '")
                    .append(productEntities.get(i).getImage()).append("', '")
                    .append(productEntities.get(i).getQuantity())
                    .append("')").append(",");

            if ((i % batchSize == 0 && i != 0) || i == productEntities.size() - 1) {
                String dataInsert = productBuilder.toString();
                dataInsert = StringUtils.substring(dataInsert, 0, dataInsert.length() - 1);
                String execQuery = queryString + dataInsert;

                Query query = entityManagerService
                        .getEntityManager()
                        .createNativeQuery(
                                execQuery,
                                ProductEntity.class
                        );

                query.executeUpdate();
                productBuilder = new StringBuilder();
            }
        }
    }
}
