package com.thesis.dms.service.product;

import com.thesis.dms.dto.product.ProductDTO;
import com.thesis.dms.entity.product.ProductEntity;
import com.thesis.dms.exception.CustomException;
import com.thesis.dms.repository.ProductRepository;
import com.thesis.dms.repository.custom.CustomProductRepository;
import com.thesis.dms.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class ProductService extends BaseService implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomProductRepository customProductRepository;
    @Override
    public List<ProductEntity> readProductFromExcel(MultipartFile file) throws IOException, CustomException {
        try {
            String fileName = file.getName();
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

            FileInputStream fis = new FileInputStream(fileName);
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

        } catch (Exception ex) {
            log.error("Import excel file failed", ex);
        }
        return null;
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

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }
}
