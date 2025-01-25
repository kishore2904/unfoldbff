package com.unfold.unfoldbff.repository;

import com.unfold.unfoldbff.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {

    String FIND_CARTITEMS_BY_USERID_WITH_PRODUCTS = """
     SELECT CARTITEMS FROM Cart CARTITEMS WHERE  CARTITEMS.userId = :userId
    """;

//    String DELETE_CART_ITEMS_BY_USERID = """
//            DELETE FROM Cart CARTITEMS WHERE CARTITEMS.userId = :userId
//            """;

    Cart findByProductId(Integer productId);

    @Query(value = FIND_CARTITEMS_BY_USERID_WITH_PRODUCTS)
    List<Cart> findByUserId(Integer userId);

//    @Query(value = DELETE_CART_ITEMS_BY_USERID)
//    void deleteByUserId(Integer userId);
}
