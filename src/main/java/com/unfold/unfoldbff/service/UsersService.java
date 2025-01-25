package com.unfold.unfoldbff.service;


import com.unfold.unfoldbff.model.entity.Users;

public interface UsersService {

    Users requestNewUser(Users users);

    void initRolesAndUser();
}
