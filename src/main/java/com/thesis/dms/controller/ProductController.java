package com.thesis.dms.controller;

import com.thesis.dms.common.constant.DefaultConst;
import com.thesis.dms.common.request.IApiName;
import com.thesis.dms.dto.product.ProductDTO;
import com.thesis.dms.entity.ResultEntity;
import com.thesis.dms.entity.product.ProductEntity;
import com.thesis.dms.service.product.IProductService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequestMapping(IApiName.PRODUCT)
public class ProductController extends BaseController{

    @Autowired
    private IProductService productService;
    @ApiOperation(value = "Import sản phẩm từ file excel", notes = "{}")
    @PostMapping("/item/import")
    public ResponseEntity<?> importFromExcelFile(@RequestParam("file") MultipartFile multipartFile) {
        try {
            return response(new ResultEntity(1, "Import product successfully", productService.readProductFromExcel(multipartFile)));
        } catch (Exception ex) {
            log.error("Import products from excel file failed!");
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
            @RequestParam(value = "sort_field", required = false, defaultValue = DefaultConst.STRING) String sort_field,
            @RequestParam(value = "sort_asc", required = false, defaultValue = DefaultConst.BOOL) Boolean sort_asc,
            @RequestParam(value = "code", required = false, defaultValue = DefaultConst.STRING) String code,
            @RequestParam(value = "name", required = false, defaultValue = DefaultConst.STRING) String name,
            @RequestParam(value = "status", required = false, defaultValue = DefaultConst.BOOL) Boolean status,
            @RequestParam(value = "color", required = false, defaultValue = DefaultConst.STRING) String color,
            @RequestParam(value = "sku", required = false, defaultValue = DefaultConst.STRING) String sku,
            Authentication authentication) {
        try {
            return response(new ResultEntity(1, "Search product data successfully", productService.search(
                    page,
                    size,
                    sort_field,
                    sort_asc,
                    code,
                    name,
                    status,
                    color,
                    sku,
                    authentication
            )));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }

    @ApiOperation("Update a product")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ProductDTO productDTO, @PathVariable("id") long id) {
        try {
            return response(new ResultEntity(1, "Update product successfully", productService.update(productDTO, id)));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }

    @ApiOperation(value = "Xoa nhieu san pham boi danh sach id")
    @DeleteMapping(value = "/deleteMultiple")
    public ResponseEntity<?> deleteProductFromList(@RequestBody Long[] ids)
    {
        try {
            productService.deleteManyProducts(ids);
            return response(new ResultEntity(1,"delete list products successfully",ids));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }

    @ApiOperation(value = "Xoa san pham boi danh sach id")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam Long id)
    {
        try {
            productService.delete(id);
            return response(new ResultEntity(1,"delete the product successfully", id));
        } catch (Exception ex) {
            return response(error(ex));
        }
    }
}
