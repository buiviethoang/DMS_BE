package com.thesis.dms.service.product;

import com.thesis.dms.entity.product.ProductEntity;
import com.thesis.dms.exception.CustomException;

import java.io.IOException;

public interface IProductService {
    void readProductFromExcel(String fileName) throws IOException, CustomException;
}
