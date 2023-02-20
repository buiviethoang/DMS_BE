package com.thesis.dms.service.product;

import com.thesis.dms.entity.product.ProductEntity;
import com.thesis.dms.exception.CustomException;
import com.thesis.dms.repository.ProductRepository;
import com.thesis.dms.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.*;
import org.hibernate.jdbc.Work;
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
@Service
@Slf4j
public class ProductService extends BaseService implements IProductService{

    @Autowired
    private ProductRepository productRepository;
    @Override
    public void readProductFromExcel(String fileName) throws IOException, CustomException {
        try {
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
                if (row.getRowNum() == 0) continue;
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
                log.info("Added product {}", productEntity);
                listProduct.add(productEntity);
            }

        } catch (Exception ex) {
            log.error("Import excel file failed", ex);
        }
    }

}
