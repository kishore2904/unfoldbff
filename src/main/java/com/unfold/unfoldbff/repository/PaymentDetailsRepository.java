package com.unfold.unfoldbff.repository;

import com.unfold.unfoldbff.model.entity.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long> {
    // Additional custom queries can be defined here if needed
}
