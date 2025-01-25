package com.unfold.unfoldbff.repository;

import com.unfold.unfoldbff.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    String FIND_PRODUCT_BY_CATEGORY_AND_PRODUCT_ID = """
        SELECT PRODUCTDETAILS FROM Product PRODUCTDETAILS WHERE
        PRODUCTDETAILS.productId = :productId AND PRODUCTDETAILS.categoryId = :categoryId
        """;

    String UPDATE_PRODUCT_BY_CATEGORY_AND_PRODUCT_ID = """
        UPDATE Product prod SET prod.productName = :productName,
        prod.productDescription = :productDescription,
        prod.price = :price, prod.stockQuantity = :stockQuantity,
        prod.imageUrl = :imageUrl WHERE prod.categoryId = :categoryId
        AND prod.productId = :productId
        """;

    String DELETE_PRODUCT_BY_CATEGORY_AND_PRODUCT_ID = """
        DELETE FROM Product prod WHERE prod.categoryId = :categoryId
        AND prod.productId = :productId
        """;

    @Query(value = FIND_PRODUCT_BY_CATEGORY_AND_PRODUCT_ID)
    Product findProductByCategoryIdAndProductId(@Param("categoryId") Integer categoryId,
                                                @Param("productId") Integer productId);

    @Modifying
    @Query(value = UPDATE_PRODUCT_BY_CATEGORY_AND_PRODUCT_ID)
    void updateProductBasedOnCategoryAndProductId(@Param("categoryId") Integer categoryId,
                                                  @Param("productId") Integer productId,
                                                  @Param("productName") String productName,
                                                  @Param("productDescription") String productDescription,
                                                  @Param("price") Double price,
                                                  @Param("stockQuantity") Integer stockQuantity,
                                                  @Param("imageUrl") String imageUrl);

    @Modifying
    @Query(value = DELETE_PRODUCT_BY_CATEGORY_AND_PRODUCT_ID)
    void deleteProductByCategoryAndProductId(@Param("categoryId") Integer categoryId,
                                             @Param("productId") Integer productId);
}
