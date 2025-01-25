package com.unfold.unfoldbff.service.impl;

import com.unfold.unfoldbff.mapper.ProductColorMapper;
import com.unfold.unfoldbff.model.dto.ProductColorDto;
import com.unfold.unfoldbff.model.entity.ProductColor;
import com.unfold.unfoldbff.repository.ProductColorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ProductColorServiceImpl {

    private  final ProductColorRepository productColorRepository;
    private final ProductColorMapper productColorMapper;

    public ProductColorServiceImpl(ProductColorRepository productColorRepository, ProductColorMapper productColorMapper) {
        this.productColorRepository = productColorRepository;
        this.productColorMapper = productColorMapper;
    }

    public void create(ProductColorDto productColorDto){
        ProductColor productColor = productColorMapper.convertToProductColor(productColorDto);
        productColorRepository.save(productColor);
    }

    public List<ProductColorDto> getAllProductColors(){
        List<ProductColor> productColors = productColorRepository.findAll();
        return productColorMapper.convertToProductColorDto(productColors);
    }

    public ProductColorDto getByColorId(Long productColorId){
        ProductColor productColor = productColorRepository.findById(productColorId).get();
        return productColorMapper.convertToProductColorDto(productColor);
    }

    public void update(Long productColorId, ProductColorDto productColorDto){
        ProductColor productColor = productColorRepository.findById(productColorId).get();
        if(productColor!=null){
            ProductColor updatedProductColor = productColorMapper.convertToProductColor(productColorDto);
            productColorRepository.save(updatedProductColor);
        }
    }

    public void delete(Long productColorId){
        productColorRepository.deleteById(productColorId);
    }
}
