package com.unfold.unfoldbff.repository;

import com.unfold.unfoldbff.model.entity.Wishlist;
import com.unfold.unfoldbff.model.entity.Users;
import com.unfold.unfoldbff.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {



    @Query("SELECT w FROM Wishlist w JOIN FETCH w.user u JOIN FETCH w.product p WHERE u.id = :userId")
    List<Wishlist> findWishlistAndUserAndProductByUserId(@Param("userId") Long userId);


    Optional<Wishlist> findByUserAndProduct(Users user, Product product);
}
