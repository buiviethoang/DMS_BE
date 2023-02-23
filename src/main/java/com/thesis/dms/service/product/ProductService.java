package com.thesis.dms.service.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.dms.common.CommonResource;
import com.thesis.dms.dto.ReturnPaginationDTO;
import com.thesis.dms.dto.product.ProductDTO;
import com.thesis.dms.dto.product.ProductResponseDTO;
import com.thesis.dms.entity.product.ProductEntity;
import com.thesis.dms.exception.CustomException;
import com.thesis.dms.repository.ProductRepository;
import com.thesis.dms.repository.custom.CustomProductRepository;
import com.thesis.dms.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.hibernate.internal.util.StringHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class ProductService extends BaseService implements IProductService{

    @Autowired
    private CommonResource commonResource;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomProductRepository customProductRepository;
    @Override
    public List<ProductEntity> readProductFromExcel(MultipartFile file) throws IOException, CustomException {
            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if (fileName.equals("")) {
                throw caughtException(2, "Hãy tải file lên hệ thống!");
            }
            String fileExt = "";
            if (fileName.contains(".")) {
                fileExt = fileName.substring(fileName.lastIndexOf("."));
            }
            if (!fileExt.contains("xls")) {
                throw caughtException(2, "Wrong file type. Only support .xls and .xlsx file extensions");
            }

            FileInputStream fis = new FileInputStream(path.replace(".", "data/file/") + fileName);
            Workbook workbook = null;
            Sheet sheet = null;
            if (fileExt.equals(".xls")) {
                workbook = new HSSFWorkbook(fis);
                sheet = workbook.getSheetAt(0);
            }
            else {
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheetAt(0);
            }
            Iterator<Row> rowIterator = sheet.iterator();
            List<ProductEntity> listProduct = new ArrayList<>();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0 || isRowEmpty(row)) continue;
                ProductEntity productEntity = new ProductEntity();
                Iterator<Cell> cellIterator = row.cellIterator();
                productEntity.setCode(row.getCell(0).getStringCellValue());
                productEntity.setName(row.getCell(1).getStringCellValue());
                productEntity.setUnitQuantity(Long.valueOf((long) row.getCell(2).getNumericCellValue()));
                productEntity.setStatus(row.getCell(6).getBooleanCellValue());
                productEntity.setColor(row.getCell(7).getStringCellValue());
                productEntity.setMassNumber((long) row.getCell(8).getNumericCellValue());
                productEntity.setSku(BigDecimal.valueOf(row.getCell(9).getNumericCellValue()));
                productEntity.setImage(row.getCell(10).getStringCellValue());
                productEntity.setQuantity(Long.valueOf((long) row.getCell(11).getNumericCellValue()));
                listProduct.add(productEntity);
            }
            log.info("Num of product added {}", listProduct.size());
            customProductRepository.updateListProduct(listProduct);
            return listProduct;
    }

    public static boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellTypeEnum() != CellType.BLANK)
                return false;
        }
        return true;
    }

    @Override
    public ProductEntity create(ProductDTO productDTO) {
        ProductEntity newProduct = new ProductEntity();
        newProduct.setCode(productDTO.getCode());
        newProduct.setName(productDTO.getName());
        newProduct.setUnitQuantity(productDTO.getUnitQuantity());
        newProduct.setStatus(productDTO.getStatus());
        newProduct.setColor(productDTO.getColor());
        newProduct.setMassNumber(productDTO.getMassNumber());
        newProduct.setSku(productDTO.getSku());
        newProduct.setImage(productDTO.getImage());
        newProduct.setQuantity(productDTO.getQuantity());
        return productRepository.save(newProduct);
    }

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    public ReturnPaginationDTO<ProductResponseDTO> search(
            int page, int size, String sort_field, boolean sort_asc, String code, String name,
            boolean status, String color, String sku,
            Authentication authentication) throws JsonProcessingException {

        Pageable pageable;
        pageable = StringHelper.isEmpty(sort_field) ? getDefaultPage(page, size)
                : sort_asc ? PageRequest.of(page - 1, size, Sort.by(sort_field).ascending())
                : PageRequest.of(page - 1, size, Sort.by(sort_field).descending());

        Page<String> listStringProduct = productRepository.search(pageable, code, name, status, color, sku);
        List<ProductResponseDTO> results = new ArrayList<>();
        for (String stringProduct: listStringProduct) {
            ObjectMapper objectMapper = new ObjectMapper();
            ProductResponseDTO responseDTO = objectMapper.readValue(stringProduct, ProductResponseDTO.class);
            results.add(responseDTO);
        }
        return getPaginationResult(results, page, listStringProduct.getTotalPages(), listStringProduct.getTotalElements());
    }



    @Override
    public ProductEntity update(ProductDTO product, Long id) throws CustomException {
        ProductEntity productEntity = productRepository.findProductById(id);

        if (productEntity == null) {
            throw caughtException(2, "Sản phẩm không tồn tại");
        }

        productEntity.setCode(product.getCode() != null ? product.getCode() : productEntity.getCode());
        productEntity.setName(product.getName() != null ? product.getName() : productEntity.getName());
        productEntity.setUnitQuantity(product.getUnitQuantity() != null ? product.getUnitQuantity() : productEntity.getUnitQuantity());
        productEntity.setStatus(product.getStatus() != null ? product.getStatus() : productEntity.getStatus());
        productEntity.setColor(product.getColor() != null ? product.getColor() : productEntity.getColor());
        productEntity.setMassNumber(product.getMassNumber() != null ? product.getMassNumber() : productEntity.getMassNumber());
        productEntity.setSku(product.getSku() != null ? product.getSku() : productEntity.getSku());
        productEntity.setImage(product.getImage() != null ? product.getImage() : productEntity.getImage());
        productEntity.setQuantity(product.getQuantity() != null ? product.getQuantity() : productEntity.getQuantity());

        log.info("Product after updated: {}", productEntity);
       return productRepository.save(productEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteManyProducts(Long[] ids) throws CustomException {
        if (ids.length == 0) {
            throw caughtException(2, "Danh sách sản phẩm cần xóa trống");
        }
        for (Long id : ids) {
            ProductEntity entity = productRepository.findProductById(id);
            if (entity == null) {
                throw caughtException(3, "Danh sách có id không tồn tại");
            }
            ProductEntity deletingProduct = productRepository.findProductById(id);
            deletingProduct.setDeleted(1);
            productRepository.save(deletingProduct);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) throws CustomException {
        if (id == null) {
            throw caughtException(2, "Chưa chọn sản phẩm cần xóa");
        }
        ProductEntity deletingProduct = productRepository.findProductById(id);
        if (deletingProduct == null) {
            throw caughtException(3, "Sản phẩm chọn không tồn tại");
        }
        deletingProduct.setDeleted(1);
        productRepository.save(deletingProduct);
    }

}
