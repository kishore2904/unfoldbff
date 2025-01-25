package com.unfold.unfoldbff.controller;

import com.unfold.unfoldbff.model.dto.ProductSizeDto;
import com.unfold.unfoldbff.service.impl.ProductSizeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/rest/unfold")
public class ProductSizeController {

    private final ProductSizeServiceImpl productSizeServiceImpl;


    public ProductSizeController(ProductSizeServiceImpl productSizeServiceImpl) {
        this.productSizeServiceImpl = productSizeServiceImpl;
    }

    @PostMapping(value="/productSize")
    public ResponseEntity<Void> createProductSize(@RequestBody ProductSizeDto productSizeDto) {
        productSizeServiceImpl.create(productSizeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value="/allProductSizes")
    public ResponseEntity<List<ProductSizeDto>> getAllProductSizes() {
        return ResponseEntity.ok(productSizeServiceImpl.getAllProductSizes());
    }

    @GetMapping("/{productSizeId}")
    public ResponseEntity<ProductSizeDto> getProductSizeById(@PathVariable Long productSizeId) {
        return ResponseEntity.ok(productSizeServiceImpl.getByProductSizeId(productSizeId));
    }

    @PutMapping("/{productSizeId}/productSize")
    public ResponseEntity<Void> updateProductSize(@PathVariable Long sizeId,
        @RequestBody ProductSizeDto productSizeDto) {
        productSizeServiceImpl.update(sizeId,productSizeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{deleteProductSizeId}")
    public ResponseEntity<Void> deleteProductSize(@PathVariable Long deleteProductSizeId) {
        productSizeServiceImpl.delete(deleteProductSizeId);
        return ResponseEntity.noContent().build();
    }
}
