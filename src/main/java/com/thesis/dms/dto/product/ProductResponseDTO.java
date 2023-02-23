package com.thesis.dms.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ProductResponseDTO {
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
