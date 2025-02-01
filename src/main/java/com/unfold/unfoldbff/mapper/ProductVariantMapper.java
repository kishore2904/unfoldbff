package com.unfold.unfoldbff.mapper;

import com.unfold.unfoldbff.model.dto.ProductVariantDto;
import com.unfold.unfoldbff.model.entity.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductVariantMapper {



    @Mapping(source="variantId", target="variantId")
    @Mapping(source="productId", target="productId")
    @Mapping(source="colorId", target="colorId")
    @Mapping(source="sizeId", target="sizeId")
    @Mapping(source="price", target="price")
    @Mapping(source="stockQuantity", target="stockQuantity")
    ProductVariant convertToProductVariant(ProductVariantDto productVariantDto);

    @Mapping(source="variantId", target="variantId")
    @Mapping(source="productId", target="productId")
    @Mapping(source="colorId", target="colorId")
    @Mapping(source="sizeId", target="sizeId")
    @Mapping(source="price", target="price")
    @Mapping(source="stockQuantity", target="stockQuantity")
    ProductVariantDto convertToProductVariantDto(ProductVariant productVariant);

    List<ProductVariantDto> convertToProductVariantDto(List<ProductVariant> productVariantList);
}
