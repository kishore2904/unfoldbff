package com.unfold.unfoldbff.mapper;


import com.unfold.unfoldbff.model.dto.CategoryDto;
import com.unfold.unfoldbff.model.dto.ProductDto;
import com.unfold.unfoldbff.model.entity.Category;
import com.unfold.unfoldbff.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "categoryName", target = "categoryName")
    @Mapping(source = "parentCategoryId", target = "parentCategoryId")
    CategoryDto convertToCategoryDto(Category category);

    List<CategoryDto> convertToCategoryDto(List<Category> categoryList);

    default List<CategoryDto> convertToCategoryDtoWithProduct(List<Category> categoryList){

        List<CategoryDto> categoryDtos = new ArrayList<>();
        for(Category category : categoryList){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setCategoryId(category.getCategoryId());
            categoryDto.setCategoryName(category.getCategoryName());
            categoryDto.setParentCategoryId(category.getParentCategoryId());
            categoryDto.setProductDtos(convertToProductDto(category.getProducts()));
            categoryDtos.add(categoryDto);
        }
       return categoryDtos;
    }

    default List<ProductDto> convertToProductDto(List<Product> productList){

        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product: productList){
            ProductDto productDto = new ProductDto();
            productDto.setCategoryId(product.getCategoryId());
            productDto.setProductDescription(product.getProductDescription());
            productDto.setProductName(product.getProductName());
            productDto.setProductId(product.getProductId());
            productDto.setImageUrl(product.getImageUrl());
            productDto.setPrice(product.getPrice());
            productDto.setStockQuantity(product.getStockQuantity());
            productDtos.add(productDto);

        }
        return  productDtos;
    }

    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "categoryName",target = "categoryName")
    @Mapping(source = "parentCategoryId", target = "parentCategoryId")
    Category convertToCategory(CategoryDto categoryDto);
}
