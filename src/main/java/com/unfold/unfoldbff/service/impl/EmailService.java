package com.unfold.unfoldbff.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendWelcomeEmail(String toEmail, String name) throws MessagingException {
        // Prepare Thymeleaf context
        Context context = new Context();
        context.setVariable("name", name);

        // Process HTML template
        String htmlContent = templateEngine.process("welcome-email", context);

        // Create email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

        helper.setTo(toEmail);
        helper.setSubject("Welcome to Our Store!");
        helper.setText(htmlContent, true); // Set HTML content

        // Send email
        mailSender.send(message);
    }

    public void sendNewsletterSubscriptionEmail(String toEmail, String name) throws MessagingException {
        // Prepare the Thymeleaf context
        Context context = new Context();
        context.setVariable("name", name);  // Pass dynamic name

        // Process the HTML template with Thymeleaf
        String htmlContent = templateEngine.process("newsletter-subscription", context);

        // Create the email message
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

        helper.setTo(toEmail);
        helper.setSubject("Welcome to Our Newsletter!");
        helper.setText(htmlContent, true); // Send HTML content

        // Send the email
        mailSender.send(message);
    }
}
