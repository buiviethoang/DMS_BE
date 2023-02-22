package com.thesis.dms.service.product;

import com.thesis.dms.dto.product.ProductDTO;
import com.thesis.dms.entity.product.ProductEntity;
import com.thesis.dms.exception.CustomException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    List<ProductEntity> readProductFromExcel(MultipartFile file) throws IOException, CustomException;

    ProductEntity create(ProductDTO productDTO);

    List<ProductEntity> findAll();
}
