package com.thesis.dms.service.product;

import com.thesis.dms.exception.CustomException;
import com.thesis.dms.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;

@Service
@Slf4j
public class ProductService extends BaseService implements IProductService{

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

            if (fileExt.equals(".xls")) {
                HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fis);
                HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            }
            else {

                XSSF hssfWorkbook = new XSSFWorkbook(fileInputStream);
                XSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            }
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                log.info("jdfjf {}", String.valueOf(row.getRowNum()));
            }
            log.info("Oke");
        } catch (Exception ex) {
            log.error("Import excel file failed", ex);
        }
    }

}
