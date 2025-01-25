package com.unfold.unfoldbff.controller;


import com.unfold.unfoldbff.model.dto.ProductDto;
import com.unfold.unfoldbff.service.impl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/rest/unfold")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @GetMapping(value="/{categoryId}/{productId}")
    public ResponseEntity<ProductDto> getProductBasedOnCategoryIdAndProductId(
            @PathVariable Integer categoryId, @PathVariable Integer productId){

        return ResponseEntity.ok(
                productServiceImpl.getProductBasedOnCategoryIdAndProductId(categoryId,productId));

    }

    @PostMapping(value="/{categoryId}/products")
    public ResponseEntity<Void> createProductsUnderCategory(
            @PathVariable Integer categoryId, @RequestBody List<ProductDto> productDtos){

        productServiceImpl.createProductsUnderCategory(categoryId,productDtos);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping(value="/{categoryId}/productUpdate")
    public ResponseEntity<Void> updateProductUnderCategory(
            @PathVariable Integer categoryId, @RequestBody ProductDto productDto){

        productServiceImpl.updateProductUnderCategory(categoryId,productDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value="/{categoryId}/{deleteProductId}")
    public ResponseEntity<Void> deleteProductByCategoryAndProductId(
            @PathVariable Integer categoryId, @PathVariable Integer productId){

        productServiceImpl.deleteProductByCategoryAndProductId(categoryId,productId);
        return ResponseEntity.noContent().build();

    }

}
