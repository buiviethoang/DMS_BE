package com.thesis.dms.cron;

import com.thesis.dms.exception.CustomException;
import com.thesis.dms.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component // have to annotate for java accept this bean.
public class ScheduleJob {
    @Autowired
    private ProductService productService;

    @Scheduled(fixedDelay = 10000)
    public void importFileDemo() throws IOException, CustomException {
        String fileName = "data/file/product_b33cf9a7_76d3_49aa_acf9_42173debf32b.xlsx";
        productService.readProductFromExcel(fileName);
    }
}
