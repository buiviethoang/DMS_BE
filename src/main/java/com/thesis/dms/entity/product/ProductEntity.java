package com.thesis.dms.entity.product;

import com.thesis.dms.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product")
@Getter
@Setter
public class ProductEntity extends BaseEntity {
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "unit_quantity")
    private String unitQuantity;
    @Column(name = "status")
    private boolean status;
    @Column(name = "description")
    private String description;
    @Column(name = "sku")
    private String sku;
//    @Column(name = "images")
//    private List<String> imageIds;
}
