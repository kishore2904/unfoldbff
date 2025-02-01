package com.unfold.unfoldbff.controller;


import com.unfold.unfoldbff.model.dto.ProductDto;
import com.unfold.unfoldbff.service.impl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.unfold.unfoldbff.utils.Constants.PROD_URL;

@RestController
@RequestMapping(value = "/rest/unfold")
@CrossOrigin(value = PROD_URL)
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @GetMapping(value = "/{categoryId}/{productId}")
    public ResponseEntity<ProductDto> getProductBasedOnCategoryIdAndProductId(
            @PathVariable Integer categoryId, @PathVariable Integer productId) {

        return ResponseEntity.ok(
                productServiceImpl.getProductBasedOnCategoryIdAndProductId(categoryId, productId));

    }
    @GetMapping(value = "/product/{productId}")
    public ResponseEntity<ProductDto> getProductDetail(@PathVariable Integer productId){
        return ResponseEntity.ok(productServiceImpl.getProductDetails(productId));
    }
    @GetMapping(value = "/{categoryId}/product")
    public ResponseEntity<List<ProductDto>> getAllProductBasedOnCategoryId(
            @PathVariable Integer categoryId) {
        List<ProductDto> productDtoList = productServiceImpl.getProductBasedOnCategoryId(categoryId);
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtoList = productServiceImpl.getAllProducts();
        return ResponseEntity.ok(productDtoList);
    }

    @PostMapping(value = "/{categoryId}/products")
    public ResponseEntity<Void> createProductsUnderCategory(
            @PathVariable Integer categoryId, @RequestBody ProductDto productDto) {
        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(productDto);
        productServiceImpl.createProductsUnderCategory(categoryId, productDtos);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping(value = "/{categoryId}/productUpdate")
    public ResponseEntity<Void> updateProductUnderCategory(
            @PathVariable Integer categoryId, @RequestBody ProductDto productDto) {

        productServiceImpl.updateProductUnderCategory(categoryId, productDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/{categoryId}/{deleteProductId}")
    public ResponseEntity<Void> deleteProductByCategoryAndProductId(
            @PathVariable Integer categoryId, @PathVariable Integer productId) {

        productServiceImpl.deleteProductByCategoryAndProductId(categoryId, productId);
        return ResponseEntity.noContent().build();

    }

}
