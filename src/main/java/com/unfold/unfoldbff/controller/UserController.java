package com.unfold.unfoldbff.controller;

import com.unfold.unfoldbff.model.entity.JwtRequest;
import com.unfold.unfoldbff.model.entity.JwtResponse;
import com.unfold.unfoldbff.model.entity.Users;
import com.unfold.unfoldbff.service.UsersService;
import com.unfold.unfoldbff.service.impl.EmailService;
import com.unfold.unfoldbff.service.impl.JwtService;
import com.unfold.unfoldbff.utils.Constants;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.unfold.unfoldbff.utils.Constants.PROD_URL;

@RestController
@CrossOrigin(origins = PROD_URL)
public class UserController {
    @Autowired
    private final UsersService usersService;

    @Autowired
    private final EmailService emailService;

    @Autowired
    private JwtService jwtService;

    public UserController(UsersService usersService, EmailService emailService) {
        this.usersService = usersService;
        this.emailService = emailService;
    }

    @PostMapping(Constants.REGISTER_NEW_USER)
    public Users registerNewUser(@RequestBody Users users) {
        return usersService.requestNewUser(users);
    }

    @GetMapping(Constants.USER_DETAIL)
    public Optional<Users> getUserDetails(@RequestParam Long userId){
        return usersService.getUserDetails(userId);
    }
    @PostMapping(Constants.LOGIN)
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws UsernameNotFoundException {
        return jwtService.createJwtToken(jwtRequest);
    }

    @PostMapping("/rest/unfold/send-welcome-email")
    public String sendWelcomeEmail() throws MessagingException {
        String email="studiovogue01@gmail.com";
        String username="Studio Vogue";
        emailService.sendWelcomeEmail(email,username);
        return "Welcome email sent to " + email;
    }
}
