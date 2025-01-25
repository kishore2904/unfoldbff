package com.unfold.unfoldbff.controller;

import com.unfold.unfoldbff.model.entity.JwtRequest;
import com.unfold.unfoldbff.model.entity.JwtResponse;
import com.unfold.unfoldbff.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JwtController {
    @Autowired
    private JwtService jwtService;

    @PostMapping(value = "/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws UsernameNotFoundException {
        return jwtService.createJwtToken(jwtRequest);
    }
}


