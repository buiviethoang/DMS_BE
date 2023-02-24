package com.thesis.dms.entity.product;

import com.thesis.dms.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "product_unit")
public class ProductUnit extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "unit", fetch = FetchType.LAZY)
    private Set<ProductEntity> product;


}
