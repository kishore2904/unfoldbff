package com.unfold.unfoldbff.service.impl;

import com.unfold.unfoldbff.model.dto.PaymentDetailsDto;
import com.unfold.unfoldbff.model.entity.Order;
import com.unfold.unfoldbff.model.entity.PaymentDetails;
import com.unfold.unfoldbff.model.entity.Users;
import com.unfold.unfoldbff.repository.OrderRepository;
import com.unfold.unfoldbff.repository.PaymentDetailsRepository;
import com.unfold.unfoldbff.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentDetailsService {

    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    @Autowired
    private OrderRepository orderRepository;  // Assuming you have an OrderRepository
    @Autowired
    private UsersRepository userRepository;    // Assuming you have a UserRepository

    // Method to create a new payment detail
    public PaymentDetailsDto createPayment(PaymentDetailsDto paymentDetailsDTO) {
        PaymentDetails paymentDetails = new PaymentDetails();

        // Mapping DTO to entity
        paymentDetails.setPaymentMethod(paymentDetailsDTO.getPaymentMethod());
        paymentDetails.setPaymentStatus(paymentDetailsDTO.getPaymentStatus());
        paymentDetails.setPaymentAmount(paymentDetailsDTO.getPaymentAmount());
        paymentDetails.setPaymentDate(paymentDetailsDTO.getPaymentDate());
        paymentDetails.setTransactionId(paymentDetailsDTO.getTransactionId());
        paymentDetails.setBillingAddress(paymentDetailsDTO.getBillingAddress());
        paymentDetails.setShippingAddress(paymentDetailsDTO.getShippingAddress());
        paymentDetails.setCardNumberLast4(paymentDetailsDTO.getCardNumberLast4());
        paymentDetails.setCardExpiryDate(paymentDetailsDTO.getCardExpiryDate());

        // Assuming that orderId and userId are valid, fetching them from DB
        Optional<Order> order = orderRepository.findById(paymentDetailsDTO.getOrderId());
        Optional<Users> user = userRepository.findById(paymentDetailsDTO.getUserId());

        if (order.isPresent() && user.isPresent()) {
            paymentDetails.setOrder(order.get());
            paymentDetails.setUser(user.get());
            PaymentDetails savedPayment = paymentDetailsRepository.save(paymentDetails);

            // Mapping entity back to DTO
            return mapToDTO(savedPayment);
        } else {
            throw new RuntimeException("Invalid order or user ID");
        }
    }

    // Method to get payment details by ID
    public PaymentDetailsDto getPaymentById(Long paymentId) {
        Optional<PaymentDetails> paymentDetails = paymentDetailsRepository.findById(paymentId);
        if (paymentDetails.isPresent()) {
            return mapToDTO(paymentDetails.get());
        } else {
            throw new RuntimeException("Payment not found for ID: " + paymentId);
        }
    }

    // Method to get all payments
    public List<PaymentDetailsDto> getAllPayments() {
        List<PaymentDetails> payments = paymentDetailsRepository.findAll();
        return payments.stream().map(this::mapToDTO).toList();
    }

    public PaymentDetailsDto updatePayment(Long paymentId, PaymentDetailsDto paymentDetailsDTO) {
        Optional<PaymentDetails> existingPayment = paymentDetailsRepository.findById(paymentId);
        if (existingPayment.isPresent()) {
            PaymentDetails paymentDetails = existingPayment.get();

            // Update the fields
            paymentDetails.setPaymentMethod(paymentDetailsDTO.getPaymentMethod());
            paymentDetails.setPaymentStatus(paymentDetailsDTO.getPaymentStatus());
            paymentDetails.setPaymentAmount(paymentDetailsDTO.getPaymentAmount());
            paymentDetails.setPaymentDate(paymentDetailsDTO.getPaymentDate());
            paymentDetails.setTransactionId(paymentDetailsDTO.getTransactionId());
            paymentDetails.setBillingAddress(paymentDetailsDTO.getBillingAddress());
            paymentDetails.setShippingAddress(paymentDetailsDTO.getShippingAddress());
            paymentDetails.setCardNumberLast4(paymentDetailsDTO.getCardNumberLast4());
            paymentDetails.setCardExpiryDate(paymentDetailsDTO.getCardExpiryDate());

            // Update order and user if provided
            Optional<Order> order = orderRepository.findById(paymentDetailsDTO.getOrderId());
            Optional<Users> user = userRepository.findById(paymentDetailsDTO.getUserId());

            if (order.isPresent()) {
                paymentDetails.setOrder(order.get());
            }
            if (user.isPresent()) {
                paymentDetails.setUser(user.get());
            }

            PaymentDetails updatedPayment = paymentDetailsRepository.save(paymentDetails);

            return mapToDTO(updatedPayment);
        } else {
            throw new RuntimeException("Payment not found for ID: " + paymentId);
        }
    }

    public void deletePaymentById(Long paymentId) {
        Optional<PaymentDetails> paymentDetails = paymentDetailsRepository.findById(paymentId);
        if (paymentDetails.isPresent()) {
            paymentDetailsRepository.delete(paymentDetails.get());
        } else {
            throw new RuntimeException("Payment not found for ID: " + paymentId);
        }
    }
    // Helper method to map from entity to DTO
    private PaymentDetailsDto mapToDTO(PaymentDetails paymentDetails) {
        PaymentDetailsDto dto = new PaymentDetailsDto();
        dto.setPaymentId(paymentDetails.getPaymentId());
        dto.setOrderId(paymentDetails.getOrder().getOrderId());
        dto.setUserId(paymentDetails.getUser().getUser_id());
        dto.setPaymentMethod(paymentDetails.getPaymentMethod());
        dto.setPaymentStatus(paymentDetails.getPaymentStatus());
        dto.setPaymentAmount(paymentDetails.getPaymentAmount());
        dto.setPaymentDate(paymentDetails.getPaymentDate());
        dto.setTransactionId(paymentDetails.getTransactionId());
        dto.setBillingAddress(paymentDetails.getBillingAddress());
        dto.setShippingAddress(paymentDetails.getShippingAddress());
        dto.setCardNumberLast4(paymentDetails.getCardNumberLast4());
        dto.setCardExpiryDate(paymentDetails.getCardExpiryDate());
        return dto;
    }
}

