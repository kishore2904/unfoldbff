package com.unfold.unfoldbff.repository;

import com.unfold.unfoldbff.model.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {

    Coupon findByCode(String code);
}
