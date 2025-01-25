package com.unfold.unfoldbff.repository;

import com.unfold.unfoldbff.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository  extends JpaRepository<Users,String> {
    Users findByEmailAddress(String emailAddress);

}
