package com.unfold.unfoldbff.service.impl;

import com.unfold.unfoldbff.model.dto.CustomerQueryDto;
import com.unfold.unfoldbff.model.entity.CustomerQuery;
import com.unfold.unfoldbff.model.entity.Users;
import com.unfold.unfoldbff.repository.CustomerQueryRepository;
import com.unfold.unfoldbff.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerQueryServiceImpl {
    private final CustomerQueryRepository customerQueryRepository;
    private final UsersRepository usersRepository;

    public CustomerQueryServiceImpl(CustomerQueryRepository customerQueryRepository, UsersRepository usersRepository) {
        this.customerQueryRepository = customerQueryRepository;
        this.usersRepository = usersRepository;
    }

    public void submitQuery(CustomerQueryDto dto) {
        Users user = dto.getUserId() != null ? usersRepository.findById(dto.getUserId()).orElse(null) : null;
        CustomerQuery customerQuery = new CustomerQuery();
        customerQuery.setEmail(dto.getEmail());
        customerQuery.setFullName(dto.getFullName());
        customerQuery.setSubject(dto.getSubject());
        customerQuery.setMessage(dto.getMessage());
        customerQuery.setUser(user);
        customerQueryRepository.save(customerQuery);
    }

    public List<CustomerQueryDto> getAllQueries() {
        return List.of();
    }

    public List<CustomerQueryDto> getQueriesByUserId(Long userId) {
        return List.of();
    }

    public boolean deleteQuery(Long id) {
        return false;
    }
}
