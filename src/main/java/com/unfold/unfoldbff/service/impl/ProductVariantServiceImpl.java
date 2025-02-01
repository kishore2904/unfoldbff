package com.unfold.unfoldbff.service.impl;

import com.unfold.unfoldbff.mapper.ProductVariantMapper;
import com.unfold.unfoldbff.model.dto.ProductVariantDto;
import com.unfold.unfoldbff.model.entity.ProductVariant;
import com.unfold.unfoldbff.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductVariantServiceImpl {

    @Autowired
    private final ProductVariantRepository productVariantRepository;
    private final ProductVariantMapper productVariantMapper;


    public ProductVariantServiceImpl(ProductVariantRepository productVariantRepository, ProductVariantMapper productVariantMapper) {
        this.productVariantRepository = productVariantRepository;
        this.productVariantMapper = productVariantMapper;
    }

    public void create(ProductVariantDto productVariantDto) {
        ProductVariant productVariant = productVariantMapper.convertToProductVariant(productVariantDto);
        productVariantRepository.save(productVariant);
    }

    public ProductVariantDto getProductVariantById(Long productVariantId) {

        ProductVariant productVariant = productVariantRepository.findById(productVariantId).get();
        return productVariantMapper.convertToProductVariantDto(productVariant);
    }

    public List<ProductVariantDto> getAllProductVariants() {
        List<ProductVariant> productVariants = productVariantRepository.findAll();
        return productVariantMapper.convertToProductVariantDto(productVariants);
    }

    public void update(Long variantId, ProductVariantDto productVariantDto) {
        ProductVariant productVariant = productVariantRepository.findById(variantId).get();
        if (productVariant != null) {
            ProductVariant updatedProductVariant = productVariantMapper.convertToProductVariant(productVariantDto);
            productVariantRepository.save(updatedProductVariant);
        }
    }

    public void deleteById(Long variantId) {
        productVariantRepository.deleteById(variantId);
    }
}
