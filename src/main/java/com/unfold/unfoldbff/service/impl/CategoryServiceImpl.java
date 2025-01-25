package com.unfold.unfoldbff.service.impl;

import com.unfold.unfoldbff.mapper.CategoryMapper;
import com.unfold.unfoldbff.model.dto.CategoryDto;
import com.unfold.unfoldbff.model.entity.Category;
import com.unfold.unfoldbff.repository.CategoryRepository;
import com.unfold.unfoldbff.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository,
                               CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDto> findAllCategories(){

        List<Category> categoryList = categoryRepository.findAll();
        return categoryMapper.convertToCategoryDto(categoryList);

    }

    public List<CategoryDto> findCategoryAndProductsByCategoryId(Integer categoryId){

        List<Category> categoryList = categoryRepository.findCategoryAndProductsByCategoryId(categoryId);
        return categoryMapper.convertToCategoryDtoWithProduct(categoryList);

    }
    public void createCategories(CategoryDto categoryDto){

        Category category = categoryMapper.convertToCategory(categoryDto);
        categoryRepository.save(category);
    }

    public void updateCategory(Integer categoryId, CategoryDto categoryDto){

        Category category = validCategory(categoryId);

        category.setCategoryName(categoryDto.getCategoryName());
        category.setParentCategoryId(categoryDto.getParentCategoryId());

        categoryRepository.save(category);
    }

    private Category validCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new RuntimeException("Category Doesn't exist")
        );
        return category;
    }

    public void deleteCategoryWithProduct(Integer categoryId){

        Category category = validCategory(categoryId);

        productRepository.deleteAll(category.getProducts());

        categoryRepository.delete(category);

    }
}
