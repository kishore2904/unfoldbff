package com.unfold.unfoldbff.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Integer orderId;
    private Integer userId;
    private LocalDate orderDate;
    private String status;
    private Double totalPrice;
    private String paymentStatus;
    private String shippingAddress;
    private LocalDateTime createdAt;
    private List<OrderItemDto> orderItemDtos;
}
