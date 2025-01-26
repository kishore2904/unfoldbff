package com.unfold.unfoldbff.controller;

import com.unfold.unfoldbff.model.entity.JwtRequest;
import com.unfold.unfoldbff.model.entity.JwtResponse;
import com.unfold.unfoldbff.model.entity.Users;
import com.unfold.unfoldbff.service.UsersService;
import com.unfold.unfoldbff.service.impl.JwtService;
import com.unfold.unfoldbff.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
    @Autowired
    private final UsersService usersService;

    @Autowired
    private JwtService jwtService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping(Constants.REGISTER_NEW_USER)
    public Users registerNewUser(@RequestBody Users users) {
        return usersService.requestNewUser(users);
    }
    @PostMapping(Constants.LOGIN)
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws UsernameNotFoundException {
        return jwtService.createJwtToken(jwtRequest);
    }
}
