package com.unfold.unfoldbff.service.impl;

import com.unfold.unfoldbff.model.dto.OrderItemDto;
import com.unfold.unfoldbff.model.entity.OrderItem;
import com.unfold.unfoldbff.mapper.OrderItemsMapper;
import com.unfold.unfoldbff.repository.OrderItemsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderItemsServiceImpl {

    private final OrderItemsRepository orderItemRepository;
    private final OrderItemsMapper orderItemMapper;

    public OrderItemsServiceImpl(OrderItemsRepository orderItemRepository, OrderItemsMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
    }

    // Create or Update OrderItem
    @Transactional
    public OrderItemDto saveOrUpdateOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = orderItemMapper.toEntity(orderItemDto);
        orderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toDto(orderItem);
    }

    // Get OrderItem by ID
    public OrderItemDto getOrderItemById(Integer orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("OrderItem not found for ID: " + orderItemId));
        return orderItemMapper.toDto(orderItem);
    }

    // Get all OrderItems
    public List<OrderItemDto> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItemMapper.toDtoList(orderItems);
    }

    // Delete OrderItem
    @Transactional
    public void deleteOrderItem(Integer orderItemId) {
        if (!orderItemRepository.existsById(orderItemId)) {
            throw new RuntimeException("OrderItem not found for ID: " + orderItemId);
        }
        orderItemRepository.deleteById(orderItemId);
    }
}
