package com.unfold.unfoldbff.controller;

import com.unfold.unfoldbff.model.dto.ProductColorDto;
import com.unfold.unfoldbff.service.impl.ProductColorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/unfold")
public class ProductColorController {

    private final ProductColorServiceImpl productColorServiceImpl;

    public ProductColorController(ProductColorServiceImpl productColorServiceImpl) {
        this.productColorServiceImpl = productColorServiceImpl;
    }


    @PostMapping(value = "/productColor")
    public ResponseEntity<Void> createProductVariant(@RequestBody ProductColorDto productColorDto) {
        productColorServiceImpl.create(productColorDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/allProductColors")
    public ResponseEntity<List<ProductColorDto>> getAllProductColors() {
        return ResponseEntity.ok(productColorServiceImpl.getAllProductColors());
    }

    @GetMapping("/productColor/{productColorId}")
    public ResponseEntity<ProductColorDto> getProductColorById(@PathVariable Long productColorId) {
        return ResponseEntity.ok(productColorServiceImpl.getByColorId(productColorId));
    }

    @PutMapping("/{productColorId}/productColor")
    public ResponseEntity<Void> updateProductColor(@PathVariable Long productColorId,
                                                   @RequestBody ProductColorDto productColorDto) {
        productColorServiceImpl.update(productColorId, productColorDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{deleteProductColorId}")
    public ResponseEntity<Void> deleteProductVariant(@PathVariable Long productColorId) {
        productColorServiceImpl.delete(productColorId);
        return ResponseEntity.noContent().build();
    }
}
