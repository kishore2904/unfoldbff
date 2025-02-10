package com.unfold.unfoldbff.mapper;

import com.unfold.unfoldbff.model.dto.ProductVariantDto;
import com.unfold.unfoldbff.model.dto.ProductImageDto;
import com.unfold.unfoldbff.model.entity.ProductVariant;
import com.unfold.unfoldbff.model.entity.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductVariantMapper {

    @Mapping(source = "variantId", target = "variantId")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "colorId", target = "colorId")
    @Mapping(source = "sizeId", target = "sizeId")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "stockQuantity", target = "stockQuantity")
    // If product images are directly associated with the product variant, you can map them here
    @Mapping(source = "productImages", target = "productImageDtos")  // Mapping List<ProductImage> to List<ProductImageDto>
    ProductVariantDto convertToProductVariantDto(ProductVariant productVariant);

    @Mapping(source = "variantId", target = "variantId")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "colorId", target = "colorId")
    @Mapping(source = "sizeId", target = "sizeId")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "stockQuantity", target = "stockQuantity")
    ProductVariant convertToProductVariant(ProductVariantDto productVariantDto);

    // Mapping List<ProductVariant> to List<ProductVariantDto>
    List<ProductVariantDto> convertToProductVariantDtoList(List<ProductVariant> productVariantList);

    // You might want to add a method to convert List<ProductImage> to List<ProductImageDto> as well
    List<ProductImageDto> convertToProductImageDtoList(List<ProductImage> productImages);
}
