package com.thesis.dms.entity.product;

import com.thesis.dms.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;

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
    private Long unitQuantity;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "color")
    private String color;
    @Column(name = "mass_no")
    private Long massNumber;
    @Column(name = "sku")
    private BigDecimal sku;
//    @Column(name = "description")
//    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "quantity")
    private Long quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private ProductUnit unit;
}
