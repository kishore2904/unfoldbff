package com.unfold.unfoldbff.repository;

import com.unfold.unfoldbff.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    String FIND_CATEGORY_WITH_PRODUCTS = """
     SELECT CATEGORY FROM Category CATEGORY LEFT JOIN FETCH CATEGORY.products
     WHERE CATEGORY.categoryId = :categoryId 
    """;

    @Query(value = FIND_CATEGORY_WITH_PRODUCTS)
    List<Category> findCategoryAndProductsByCategoryId(@Param("categoryId") Integer categoryId);

}
