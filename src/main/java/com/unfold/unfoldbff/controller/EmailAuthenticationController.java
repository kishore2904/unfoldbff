package com.unfold.unfoldbff.controller;

import com.unfold.unfoldbff.model.entity.VerificationCode;
import com.unfold.unfoldbff.repository.VerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static com.unfold.unfoldbff.utils.Constants.PROD_URL;

@RestController
@RequestMapping(value = "/rest/unfold")
@CrossOrigin(value = PROD_URL)
public class EmailAuthenticationController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    // Send or Resend Verification Code
    @PostMapping("/send-verification-code")
    public ResponseEntity<Void> sendVerificationCode(@RequestBody EmailRequest request) {
        String email = request.getEmail();
        String verificationCode = generateVerificationCode();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(5); // Code expires in 5 minutes

        // Remove existing code if present
        verificationCodeRepository.deleteByEmail(email);

        // Save new code in DB
        VerificationCode newCode = new VerificationCode(email, verificationCode, expirationTime);
        verificationCodeRepository.save(newCode);

        sendEmail(email, verificationCode);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Verify the code
    @PostMapping("/verify-code")
    public ResponseEntity<Void> verifyCode(@RequestBody VerificationRequest request) {
        Optional<VerificationCode> storedCodeOpt = verificationCodeRepository.findByEmail(request.getEmail());

        if (storedCodeOpt.isPresent()) {
            VerificationCode storedCode = storedCodeOpt.get();

            if (storedCode.getCode().equals(request.getCode())) {
                verificationCodeRepository.deleteByEmail(request.getEmail()); // Delete after successful verification
               return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.noContent().build();
    }

    private String generateVerificationCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    private void sendEmail(String email, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your Verification Code");
        message.setText("Your verification code is: " + verificationCode + "\nThis code expires in 5 minutes.");
        mailSender.send(message);
    }

    // Request DTOs
    public static class EmailRequest {
        private String email;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

    public static class VerificationRequest {
        private String email;
        private String code;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
    }
}