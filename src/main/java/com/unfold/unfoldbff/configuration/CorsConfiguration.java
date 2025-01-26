package com.unfold.unfoldbff.configuration;

import com.unfold.unfoldbff.utils.Constants;
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
                registry.addMapping("/**")  // Apply to all endpoints
                        .allowedOrigins(Constants.PROD_URL)  // Allow your local frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allowed HTTP methods
                        .allowedHeaders("*")  // Allow any header
                        .allowCredentials(true);  // Allow credentials (cookies or authorization)
            }
        };
    }
}

