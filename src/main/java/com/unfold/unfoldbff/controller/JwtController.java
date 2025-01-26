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

import static com.unfold.unfoldbff.utils.Constants.PROD_URL;

@RestController
@CrossOrigin(origins = PROD_URL)
public class JwtController {
    @Autowired
    private JwtService jwtService;

    @PostMapping(value = "/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws UsernameNotFoundException {
        return jwtService.createJwtToken(jwtRequest);
    }
}


