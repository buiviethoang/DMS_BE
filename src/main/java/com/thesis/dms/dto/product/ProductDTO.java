package com.thesis.dms.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {
    private String code;
    private String name;
    private Long unitQuantity;
    private Boolean status;
    private String color;
    private Long massNumber;
    private BigDecimal sku;
    private String image;
    private Long quantity;
}
