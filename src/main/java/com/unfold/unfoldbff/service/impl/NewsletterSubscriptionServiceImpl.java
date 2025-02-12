package com.unfold.unfoldbff.service.impl;

import com.unfold.unfoldbff.exception.NotFoundException;
import com.unfold.unfoldbff.model.entity.NewsletterSubscription;
import com.unfold.unfoldbff.repository.NewsletterSubscriptionRepository;
import com.unfold.unfoldbff.utils.ErrorMessage;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class NewsletterSubscriptionServiceImpl {

    private final NewsletterSubscriptionRepository repository;
    private final EmailService emailService;


    public NewsletterSubscriptionServiceImpl(NewsletterSubscriptionRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    @Transactional
    public ResponseEntity subscribe(String email) throws MessagingException {
        NewsletterSubscription subscription = new NewsletterSubscription();

        if(repository.existsByEmail(email)){
            throw new NotFoundException(ErrorMessage.USER_NAME_ALREADY_EXIST);
        }
        subscription.setEmail(email);
        repository.save(subscription);
        emailService.sendNewsletterSubscriptionEmail(email,null);
        return null;
    }
}
