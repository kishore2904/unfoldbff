package com.unfold.unfoldbff.service.impl;

import com.unfold.unfoldbff.model.dto.OrderItemDto;
import com.unfold.unfoldbff.model.entity.OrderItem;
import com.unfold.unfoldbff.mapper.OrderItemsMapper;
import com.unfold.unfoldbff.repository.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemsServiceImpl {

    private final OrderItemsRepository orderItemRepository;
    private final OrderItemsMapper orderItemMapper;


    public OrderItemsServiceImpl(OrderItemsRepository orderItemRepository, OrderItemsMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
    }

    // Create or Update OrderItem
    public OrderItemDto saveOrUpdateOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = orderItemMapper.convertToOrderItem(orderItemDto);
        orderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.convertToOrderItemDto(orderItem);
    }

    // Get OrderItem by ID
    public OrderItemDto getOrderItemById(Integer orderItemId) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(orderItemId);
        if (orderItem.isPresent()) {
            return orderItemMapper.convertToOrderItemDto(orderItem.get());
        }
        return null; // or throw a custom exception
    }

    // Get all OrderItems
    public List<OrderItemDto> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItemMapper.convertToOrderItemDtoList(orderItems);
    }

    // Delete OrderItem
    public void deleteOrderItem(Integer orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }

    // Custom query example (optional)
    // public List<OrderItemDto> getOrderItemsByOrderId(Integer orderId) {
    //     List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
    //     return orderItemMapper.convertToOrderItemDtoList(orderItems);
    // }
}
