package com.unfold.unfoldbff.controller;

import com.unfold.unfoldbff.model.dto.CouponDto;
import com.unfold.unfoldbff.service.impl.CouponServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.unfold.unfoldbff.utils.Constants.PROD_URL;

@RestController
@RequestMapping(value = "/rest/unfold")
@CrossOrigin(value = PROD_URL)
public class CouponController {
    private final CouponServiceImpl couponService;

    public CouponController(CouponServiceImpl couponService) {
        this.couponService = couponService;
    }

    @GetMapping("/allCoupon")
    public ResponseEntity<List<CouponDto>> getAllCoupon(){
        return ResponseEntity.ok(couponService.getAllCoupons());
    }

    @GetMapping("/coupon")
    public ResponseEntity<CouponDto> getCoupon(@RequestParam String code){
        return ResponseEntity.ok(couponService.getCouponByCode(code));
    }
    @PostMapping("/coupon")
    public ResponseEntity<CouponDto> addNewCoupon(@RequestBody CouponDto couponDto){
        return ResponseEntity.ok(couponService.createCoupon(couponDto));
    }
}
