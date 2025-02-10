package com.unfold.unfoldbff.model.dto;

import com.unfold.unfoldbff.model.entity.ProductImage;

import java.math.BigDecimal;
import java.util.List;

public class ProductVariantDto {

    private Long variantId;
    private Long productId;
    private Long colorId;
    private Long sizeId;
    private BigDecimal price;
    private Integer stockQuantity;
    private List<ProductImageDto> productImageDtos;

    public Long getVariantId() {
        return variantId;
    }

    public void setVariantId(Long variantId) {
        this.variantId = variantId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public List<ProductImageDto> getProductImageDtos() {
        return productImageDtos;
    }

    public void setProductImageDtos(List<ProductImageDto> productImageDtos) {
        this.productImageDtos = productImageDtos;
    }
}
