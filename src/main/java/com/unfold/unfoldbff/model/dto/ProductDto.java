package com.unfold.unfoldbff.model.dto;

import java.util.List;

public class ProductDto {

    private Integer productId;
    private String productName;
    private String productDescription;
    private double price;
    private Integer stockQuantity;
    private Integer categoryId;
    private String imageUrl;
    private List<ProductVariantDto> productVariantDtos;

    // Getters and Setters

    public List<ProductVariantDto> getProductVariantDtos() {
        return productVariantDtos;
    }

    public void setProductVariantDtos(List<ProductVariantDto> productVariantDtos) {
        this.productVariantDtos = productVariantDtos;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
