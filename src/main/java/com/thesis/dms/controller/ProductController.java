package com.thesis.dms.controller;

import com.thesis.dms.common.constant.DefaultConst;
import com.thesis.dms.common.request.IApiName;
import com.thesis.dms.dto.product.ProductDTO;
import com.thesis.dms.entity.ResultEntity;
import com.thesis.dms.entity.product.ProductEntity;
import com.thesis.dms.service.product.IProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(IApiName.PRODUCT)
public class ProductController extends BaseController{

    @Autowired
    private IProductService productService;
    @ApiOperation(value = "Import sản phẩm từ file excel", notes = "{}")
    @GetMapping("/item/import")
    public ResponseEntity<?> importFromExcelFile(@RequestParam("file") MultipartFile multipartFile) {
        try {
            return response(new ResultEntity(1, "Import product successfully", productService.readProductFromExcel(multipartFile)));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }
    
    @ApiOperation(value = "Thêm mới sản phẩm")
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ProductDTO productDTO) {
        try {
            return response(new ResultEntity(1, "Create product successfully", productService.create(productDTO)));

        }
        catch (Exception ex) {
            return response(error(ex));
        }
    }
    @ApiOperation(value = "Get all products from produtlist")
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        try {
            List<ProductEntity> products = productService.findAll();
            Map<String, Object> productMap = new HashMap<>();
            productMap.put("data", products);
            productMap.put("total", products.size());
            return response(new ResultEntity(1, "Get all product successfully", productMap));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }

    @ApiOperation(value = "Searching product by conditions", notes = "{}")
    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(value = "page", required = false, defaultValue = DefaultConst.PAGE) Integer page,
            @RequestParam(value = "size", required = false, defaultValue = DefaultConst.SIZE) Integer size,
            @RequestParam(value = "type", required = false, defaultValue = DefaultConst.NUMBER) int type,
            @RequestParam(value = "name", required = false, defaultValue = DefaultConst.STRING) String name,
            @RequestParam(value = "vendor_id", required = false, defaultValue = DefaultConst.NUMBER) Long vendorId,
            @RequestParam(value = "product_group_id", required = false, defaultValue = DefaultConst.NUMBER) Long ProductGroupId,
            @RequestParam(value = "warehouseId", required = true, defaultValue = DefaultConst.NUMBER) Long warehouseId,
            @RequestParam(value = "product_code", required = false, defaultValue = DefaultConst.STRING) String productCode,
            @RequestParam(value = "vendor_currency", required = false, defaultValue = DefaultConst.STRING) String vendorCurrency,
            @RequestParam(value = "up_price", required = false, defaultValue = DefaultConst.UP_PRICE) Float upPrice,
            @RequestParam(value = "down_price", required = false, defaultValue = DefaultConst.DOWN_PRICE) Float downPrice,
            @RequestParam(value = "status", required = false, defaultValue = DefaultConst.NUMBER) Integer status,
            @RequestParam(value = "active", required = false, defaultValue = DefaultConst.NUMBER) Integer active,
            Authentication authentication) {
        try {
            return response(new ResultEntity(1, "search product data susses", productService.searchV3(
                    page,
                    size,
                    type,
                    name,
                    vendorId,
                    ProductGroupId,
                    warehouseId,
                    productCode,
                    vendorCurrency,
                    upPrice,
                    downPrice,
                    status,
                    active,
                    authentication
            )));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }
}
