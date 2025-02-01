package com.unfold.unfoldbff.controller;

import com.unfold.unfoldbff.model.dto.ProductVariantDto;
import com.unfold.unfoldbff.service.impl.ProductVariantServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/unfold")
public class ProductVariantController {

    private final ProductVariantServiceImpl productVariantServiceImpl;


    public ProductVariantController(ProductVariantServiceImpl productVariantServiceImpl) {
        this.productVariantServiceImpl = productVariantServiceImpl;
    }

    @PostMapping(value = "/productVariant")
    public ResponseEntity<Void> createProductVariant(@RequestBody ProductVariantDto productVariantDto) {
        productVariantServiceImpl.create(productVariantDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/allProductVariants")
    public ResponseEntity<List<ProductVariantDto>> getAllProductVariants() {
        return ResponseEntity.ok(productVariantServiceImpl.getAllProductVariants());
    }

    @GetMapping("/{variantId}")
    public ResponseEntity<ProductVariantDto> getProductVariantById(@PathVariable Long variantId) {
        return ResponseEntity.ok(productVariantServiceImpl.getProductVariantById(variantId));
    }

    @PutMapping("/{variantId}/productVariant")
    public ResponseEntity<Void> updateProductVariant(@PathVariable Long variantId,
                                                     @RequestBody ProductVariantDto productVariantDto) {
        productVariantServiceImpl.update(variantId, productVariantDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{deleteVariantId}")
    public ResponseEntity<Void> deleteProductVariant(@PathVariable Long variantId) {
        productVariantServiceImpl.deleteById(variantId);
        return ResponseEntity.noContent().build();
    }
}
