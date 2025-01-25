package com.unfold.unfoldbff.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                System.out.println("****** Starting cors config******");
                registry.addMapping("/**")  // Apply to all endpoints
                        .allowedOrigins("http://localhost:4200","https://unfold.fit")  // Allow your local frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allowed HTTP methods
                        .allowedHeaders("*")  // Allow any header
                        .allowCredentials(true);  // Allow credentials (cookies or authorization)
                System.out.println("****** Ending cors config******");
            }
        };
    }
}

