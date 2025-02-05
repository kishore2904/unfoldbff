package com.unfold.unfoldbff.mapper;

import com.unfold.unfoldbff.model.dto.OrderDto;
import com.unfold.unfoldbff.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = OrderItemsMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    @Mapping(source = "orderItemDtos", target = "orderItems") // ✅ Correct mapping using OrderItemsMapper
    Order convertToOrder(OrderDto orderDto);

    @Mapping(source = "orderItems", target = "orderItemDtos") // ✅ Correct mapping using OrderItemsMapper
    OrderDto convertToOrderDto(Order order);

    List<OrderDto> convertToOrderDto(List<Order> orders);
}
