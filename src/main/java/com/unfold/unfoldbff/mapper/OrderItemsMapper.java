package com.unfold.unfoldbff.mapper;

import com.unfold.unfoldbff.model.dto.OrderItemDto;
import com.unfold.unfoldbff.model.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderItemsMapper {

    @Mapping(source = "orderItemId", target = "orderItemId")
    @Mapping(source = "order.orderId", target = "orderId")
    @Mapping(source = "product.productId", target = "productId")
    @Mapping(source = "productVariant.variantId", target = "variantId")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "priceAtTimeOfOrder", target = "priceAtTimeOfOrder")
    OrderItemDto convertToOrderItemDto(OrderItem orderItem);

    @Mapping(source = "orderItemId", target = "orderItemId")
    @Mapping(source = "orderId", target = "order.orderId")
    @Mapping(source = "productId", target = "product.productId")
    @Mapping(source = "variantId", target = "productVariant.variantId")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "priceAtTimeOfOrder", target = "priceAtTimeOfOrder")
    OrderItem convertToOrderItem(OrderItemDto orderItemDto);

    List<OrderItemDto> convertToOrderItemDtoList(List<OrderItem> orderItems);
}
