package com.unfold.unfoldbff.controller;

import com.unfold.unfoldbff.model.dto.OrderItemDto;
import com.unfold.unfoldbff.service.impl.OrderItemsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.unfold.unfoldbff.utils.Constants.PROD_URL;

@RestController
@CrossOrigin(origins = PROD_URL)
@RequestMapping(value = "/rest/unfold/order-items")
public class OrderItemsController {

    private final OrderItemsServiceImpl orderItemService;

    public OrderItemsController(OrderItemsServiceImpl orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<OrderItemDto> createOrderItems(
            @RequestBody OrderItemDto orderItemDtos) {
        return ResponseEntity.ok(orderItemService.saveOrUpdateOrderItem(orderItemDtos));
    }
}
