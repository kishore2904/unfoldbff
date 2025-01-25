package com.unfold.unfoldbff.mapper;

import com.unfold.unfoldbff.model.dto.OrderDto;
import com.unfold.unfoldbff.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    @Mapping(source = "orderId", target = "orderId")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "orderDate", target = "orderDate")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "paymentStatus", target = "paymentStatus")
    @Mapping(source = "totalPrice", target = "totalPrice")
    @Mapping(source = "shippingAddress", target = "shippingAddress")
    @Mapping(source = "createdAt", target = "createdAt")
    Order convertToOrder(OrderDto orderDto);

    @Mapping(source = "orderId", target = "orderId")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "orderDate", target = "orderDate")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "paymentStatus", target = "paymentStatus")
    @Mapping(source = "totalPrice", target = "totalPrice")
    @Mapping(source = "shippingAddress", target = "shippingAddress")
    @Mapping(source = "createdAt", target = "createdAt")
    OrderDto convertToOrderDto(Order order);

    List<OrderDto> convertToOrderDto(List<Order> orders);
}
