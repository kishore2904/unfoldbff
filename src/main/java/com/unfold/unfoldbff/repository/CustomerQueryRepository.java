package com.unfold.unfoldbff.repository;


import com.unfold.unfoldbff.model.entity.CustomerQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerQueryRepository extends JpaRepository<CustomerQuery, Long> {

}
