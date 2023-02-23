package com.thesis.dms.service.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thesis.dms.dto.ReturnPaginationDTO;
import com.thesis.dms.dto.product.ProductDTO;
import com.thesis.dms.dto.product.ProductResponseDTO;
import com.thesis.dms.entity.product.ProductEntity;
import com.thesis.dms.exception.CustomException;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    List<ProductEntity> readProductFromExcel(MultipartFile file) throws IOException, CustomException;

    ProductEntity create(ProductDTO productDTO);

    List<ProductEntity> findAll();

    ReturnPaginationDTO<ProductResponseDTO> search(
            int page, int size, String sort_field, boolean sort_asc, String code, String name, boolean status, String color, String sku, Authentication authentication
    ) throws JsonProcessingException;

    ProductEntity update(ProductDTO product, Long id) throws CustomException;

    void deleteManyProducts(Long[] ids) throws CustomException;

    void delete(Long id) throws CustomException;
}
