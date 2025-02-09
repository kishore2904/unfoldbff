package com.unfold.unfoldbff.service;


import com.unfold.unfoldbff.model.entity.Users;

import java.util.Optional;

public interface UsersService {

    Users requestNewUser(Users users);

    void initRolesAndUser();

    Optional<Users> getUserDetails(Long userId);
}
