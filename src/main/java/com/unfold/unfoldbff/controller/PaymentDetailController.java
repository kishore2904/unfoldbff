package com.unfold.unfoldbff.controller;

import com.unfold.unfoldbff.model.dto.PaymentDetailsDto;
import com.unfold.unfoldbff.service.impl.PaymentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.unfold.unfoldbff.utils.Constants.PROD_URL;

@RestController
@RequestMapping(value = "/rest/unfold")
@CrossOrigin(value = PROD_URL)
public class PaymentDetailController {

    @Autowired
    private PaymentDetailsService paymentDetailsService;

    // Create a new payment
    @PostMapping("/payment/create")
    public ResponseEntity<PaymentDetailsDto> createPayment(@RequestBody PaymentDetailsDto paymentDetailsDTO) {
        try {
            PaymentDetailsDto createdPayment = paymentDetailsService.createPayment(paymentDetailsDTO);
            return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Get payment details by payment ID
    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<PaymentDetailsDto> getPaymentById(@PathVariable Long paymentId) {
        try {
            PaymentDetailsDto payment = paymentDetailsService.getPaymentById(paymentId);
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get all payments
    @GetMapping("/payment/getPayment")
    public ResponseEntity<List<PaymentDetailsDto>> getAllPayments() {
        List<PaymentDetailsDto> payments = paymentDetailsService.getAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @PutMapping("/payment/update/{paymentId}")
    public ResponseEntity<PaymentDetailsDto> updatePayment(
            @PathVariable Long paymentId,
            @RequestBody PaymentDetailsDto paymentDetailsDTO) {
        try {
            PaymentDetailsDto updatedPayment = paymentDetailsService.updatePayment(paymentId, paymentDetailsDTO);
            return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Delete payment details by payment ID
    @DeleteMapping("/payment/delete/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable Long paymentId) {
        try {
            paymentDetailsService.deletePaymentById(paymentId);
            return new ResponseEntity<>("Payment deleted successfully", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Payment not found", HttpStatus.NOT_FOUND);
        }
    }
}

