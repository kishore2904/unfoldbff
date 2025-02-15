package com.unfold.unfoldbff.controller;

import com.unfold.unfoldbff.model.dto.OrderDto;
import com.unfold.unfoldbff.service.impl.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/unfold")
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;

    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @GetMapping(value = "/allOrders")
    public ResponseEntity<List<OrderDto>> findAllOrder() {
        return ResponseEntity.ok(orderServiceImpl.findAll());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderDto> findOrderByOrderId(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderServiceImpl.findById(orderId));
    }

    @GetMapping("/order/{userId}")
    public ResponseEntity<List<OrderDto>> findOrderByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(orderServiceImpl.findByUserId(userId));
    }

    @GetMapping("/order/status/{status}")
    public ResponseEntity<List<OrderDto>> findOrderByStatus(@PathVariable String status) {
        return ResponseEntity.ok(orderServiceImpl.findByStatus(status));
    }

    @GetMapping("/order/{paymentStatus}")
    public ResponseEntity<List<OrderDto>> findOrderByPaymentStatus(@PathVariable String paymentStatus) {
        return ResponseEntity.ok(orderServiceImpl.findByPaymentStatus(paymentStatus));
    }

    @PostMapping("/orders")
    ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {

        return ResponseEntity.ok(orderServiceImpl.create(orderDto));
    }

    @PostMapping(value = "/orders/{orderId}")
    ResponseEntity<Void> updateCategory(@PathVariable Integer orderId, @RequestBody OrderDto orderDto) {

        orderServiceImpl.update(orderId, orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/orders/{deleteId}")
    ResponseEntity<Void> deleteCategoryWithProduct(@PathVariable Integer orderId) {

        orderServiceImpl.delete(orderId);
        return ResponseEntity.noContent().build();

    }
}
