package com.unfold.unfoldbff.service.impl;

import com.unfold.unfoldbff.model.entity.Role;
import com.unfold.unfoldbff.repository.RoleRepository;
import com.unfold.unfoldbff.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }
}
