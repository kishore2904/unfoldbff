package com.unfold.unfoldbff.model.dto;

import com.unfold.unfoldbff.utils.WishlistStatus;

import java.time.LocalDateTime;

public class WishlistDto {

    private Long wishlistId;
    private Long userId;
    private Long productId;
    private LocalDateTime dateAdded;
    private WishlistStatus status;

    // Getters and setters

    public Long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public WishlistStatus getStatus() {
        return status;
    }

    public void setStatus(WishlistStatus status) {
        this.status = status;
    }
}

