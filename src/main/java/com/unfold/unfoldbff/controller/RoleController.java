package com.unfold.unfoldbff.controller;

import com.unfold.unfoldbff.model.entity.Role;
import com.unfold.unfoldbff.service.RoleService;
import com.unfold.unfoldbff.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping(Constants.CREATE_ROLE)
    public Role createRole(@RequestBody Role role){
        return roleService.createRole(role);
    }
}
