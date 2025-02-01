package com.unfold.unfoldbff.repository;

import com.unfold.unfoldbff.model.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant,Long> {
    List<ProductVariant> findByProductId(Integer productId);
}
