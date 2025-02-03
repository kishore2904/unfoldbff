package com.unfold.unfoldbff.repository;

import com.unfold.unfoldbff.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItem, Integer> {

}
