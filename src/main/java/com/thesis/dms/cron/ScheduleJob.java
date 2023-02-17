package com.thesis.dms.cron;

import com.thesis.dms.exception.CustomException;
import com.thesis.dms.service.product.ProductService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Component // have to annotate for java accept this bean.
public class ScheduleJob {
    @Autowired
    private ProductService productService;

    @Scheduled(fixedDelay = 10000)
    public void importFileDemo() throws IOException, CustomException {
        String fileName = "data/file/[template]-checklist-handover_cb2ed509_52ad_4e02_9b10_01a7dea2a788.xls";
        productService.readProductFromExcel(fileName);
    }
}
