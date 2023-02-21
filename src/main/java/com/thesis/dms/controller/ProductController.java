package com.thesis.dms.controller;

import com.thesis.dms.common.request.IApiName;
import com.thesis.dms.entity.ResultEntity;
import com.thesis.dms.service.product.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(IApiName.PRODUCT)
public class ProductController extends BaseController{

    @Autowired
    private ProductService productService;
    @ApiOperation(value = "Import sản phẩm từ file excel", notes = "{}")
    @GetMapping("/item/import")
    public ResponseEntity<?> importFromExcelFile(@RequestParam("file") MultipartFile multipartFile) {
        try {
            return response(new ResultEntity(1, "Import product successfully", productService.readProductFromExcel(multipartFile)));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }
}
