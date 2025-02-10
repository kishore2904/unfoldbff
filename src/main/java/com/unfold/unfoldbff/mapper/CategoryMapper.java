package com.unfold.unfoldbff.mapper;

import com.unfold.unfoldbff.model.dto.CategoryDto;
import com.unfold.unfoldbff.model.dto.ProductDto;
import com.unfold.unfoldbff.model.dto.ProductVariantDto;
import com.unfold.unfoldbff.model.entity.Category;
import com.unfold.unfoldbff.model.entity.Product;
import com.unfold.unfoldbff.model.entity.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ProductMapper.class, ProductVariantMapper.class})
public interface CategoryMapper {

    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "categoryName", target = "categoryName")
    @Mapping(source = "parentCategoryId", target = "parentCategoryId")
    CategoryDto convertToCategoryDto(Category category);

    List<CategoryDto> convertToCategoryDto(List<Category> categoryList);

    default List<CategoryDto> convertToCategoryDtoWithProduct(List<Category> categoryList) {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryDto categoryDto = convertToCategoryDto(category);
            categoryDto.setProductDtos(convertToProductDto(category.getProducts()));
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }

    // Reusing ProductMapper for the conversion
    default List<ProductDto> convertToProductDto(List<Product> productList) {
        return productMapper.convertToProductDtoList(productList);
    }

    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "categoryName", target = "categoryName")
    @Mapping(source = "parentCategoryId", target = "parentCategoryId")
    Category convertToCategory(CategoryDto categoryDto);

    // Injecting ProductMapper here so that it can be used
    ProductMapper productMapper = new ProductMapper() {
        @Override
        public ProductDto convertToProductDto(Product product) {
            return null;
        }

        @Override
        public Product convertToProduct(ProductDto productDto) {
            return null;
        }

        @Override
        public List<Product> convertToProduct(List<ProductDto> productDtoList) {
            return List.of();
        }

        @Override
        public List<ProductDto> convertToProductDtoList(List<Product> products) {
            return List.of();
        }
        // Implement the methods if needed.
    };
}
