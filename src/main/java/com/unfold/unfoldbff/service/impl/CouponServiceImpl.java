package com.unfold.unfoldbff.service.impl;

import com.unfold.unfoldbff.exception.NotFoundException;
import com.unfold.unfoldbff.mapper.CouponMapper;
import com.unfold.unfoldbff.model.dto.CouponDto;
import com.unfold.unfoldbff.model.entity.Coupon;
import com.unfold.unfoldbff.repository.CouponRepository;
import com.unfold.unfoldbff.utils.ErrorMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl {
    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    public CouponServiceImpl(CouponRepository couponRepository, CouponMapper couponMapper) {
        this.couponRepository = couponRepository;
        this.couponMapper = couponMapper;
    }

    public CouponDto getCouponByCode(String code) {
        Coupon coupon = couponRepository.findByCode(code);
        if (coupon == null) {
            throw new NotFoundException(ErrorMessage.INVALID_COUPON);
        } else {
            return couponMapper.convertToCouponDto(coupon);
        }
    }

    public List<CouponDto> getAllCoupons() {
        List<Coupon> coupons = couponRepository.findAll();
        return couponMapper.convertToCouponDtoList(coupons);
    }

    public CouponDto createCoupon(CouponDto couponDto) {
        Coupon coupon = couponRepository.findByCode(couponDto.getCode());
        if (coupon != null) {
            throw new NotFoundException(ErrorMessage.COUPON_ALREADY_EXIST);
        } else {
            coupon = couponMapper.convertToCoupon(couponDto);
            coupon = couponRepository.save(coupon);
            return couponMapper.convertToCouponDto(coupon);
        }
    }

    public CouponDto updateCoupon(Long id, CouponDto CouponDto) {
        Coupon coupon = couponRepository.findById(id).orElseThrow(() -> new RuntimeException("Coupon not found"));
        coupon.setCode(CouponDto.getCode());
        coupon.setDiscountAmount(CouponDto.getDiscountAmount());
        coupon.setDiscountType(CouponDto.getDiscountType());
        coupon.setValidFrom(CouponDto.getValidFrom());
        coupon.setValidTill(CouponDto.getValidTill());
        coupon.setIsActive(CouponDto.getIsActive());
        coupon = couponRepository.save(coupon);
        return couponMapper.convertToCouponDto(coupon);
    }
}
