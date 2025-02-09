package com.unfold.unfoldbff.mapper;

import com.unfold.unfoldbff.model.dto.OrderItemDto;
import com.unfold.unfoldbff.model.entity.OrderItem;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemsMapper {

    @Mapping(source = "order.orderId", target = "orderId") // ✅ Mapping Order entity to orderId
    @Mapping(source = "product.productId", target = "productId") // ✅ Mapping Product entity to productId
    @Mapping(source = "productVariant.variantId", target = "variantId") // ✅ Mapping ProductVariant entity to variantId
    OrderItemDto toDto(OrderItem orderItem);

    @Mapping(source = "orderId", target = "order.orderId") // ✅ Mapping orderId to Order entity
    @Mapping(source = "productId", target = "product.productId") // ✅ Mapping productId to Product entity
    @Mapping(source = "variantId", target = "productVariant.variantId") // ✅ Mapping variantId to ProductVariant entity
    OrderItem toEntity(OrderItemDto orderItemDto);

    List<OrderItemDto> toDtoList(List<OrderItem> orderItems);
    List<OrderItem> toEntityList(List<OrderItemDto> orderItemDtos);
}
