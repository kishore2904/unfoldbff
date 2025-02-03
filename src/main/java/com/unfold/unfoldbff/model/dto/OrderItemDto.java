package com.unfold.unfoldbff.model.dto;

public class OrderItemDto {

    private Integer orderItemId;
    private Integer orderId;
    private Integer productId;
    private Integer variantId;
    private Integer quantity;
    private Double priceAtTimeOfOrder;

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getVariantId() {
        return variantId;
    }

    public void setVariantId(Integer variantId) {
        this.variantId = variantId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPriceAtTimeOfOrder() {
        return priceAtTimeOfOrder;
    }

    public void setPriceAtTimeOfOrder(Double priceAtTimeOfOrder) {
        this.priceAtTimeOfOrder = priceAtTimeOfOrder;
    }
}
