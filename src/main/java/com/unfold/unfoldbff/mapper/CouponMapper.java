package com.unfold.unfoldbff.mapper;

import com.unfold.unfoldbff.model.dto.CouponDto;
import com.unfold.unfoldbff.model.entity.Coupon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CouponMapper {

    // Convert CouponDTO to Coupon Entity
    @Mapping(source = "code", target = "code")
    @Mapping(source = "discountAmount", target = "discountAmount")
    @Mapping(source = "discountType", target = "discountType")
    @Mapping(source = "validFrom", target = "validFrom")
    @Mapping(source = "validTill", target = "validTill")
    @Mapping(source = "isActive", target = "isActive")
    Coupon convertToCoupon(CouponDto couponDTO);

    // Convert Coupon Entity to CouponDTO
    @Mapping(source = "code", target = "code")
    @Mapping(source = "discountAmount", target = "discountAmount")
    @Mapping(source = "discountType", target = "discountType")
    @Mapping(source = "validFrom", target = "validFrom")
    @Mapping(source = "validTill", target = "validTill")
    @Mapping(source = "isActive", target = "isActive")
    CouponDto convertToCouponDto(Coupon coupon);

    // Convert a List of Coupon Entities to a List of CouponDTOs
    List<CouponDto> convertToCouponDtoList(List<Coupon> coupons);
}

