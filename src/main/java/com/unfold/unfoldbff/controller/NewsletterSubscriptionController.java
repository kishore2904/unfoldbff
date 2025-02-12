package com.unfold.unfoldbff.controller;

import com.unfold.unfoldbff.service.impl.NewsletterSubscriptionServiceImpl;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.unfold.unfoldbff.utils.Constants.PROD_URL;


@RestController
@RequestMapping(value = "/rest/unfold")
@CrossOrigin(value = PROD_URL)
public class NewsletterSubscriptionController {

    private final NewsletterSubscriptionServiceImpl service;

    public NewsletterSubscriptionController(NewsletterSubscriptionServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/subscribe")
    public ResponseEntity subscribe(@RequestParam String email) throws MessagingException {
        return service.subscribe(email);
    }
}
