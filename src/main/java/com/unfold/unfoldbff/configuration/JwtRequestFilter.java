package com.unfold.unfoldbff.configuration;

import com.unfold.unfoldbff.service.impl.JwtService;
import com.unfold.unfoldbff.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtService jwtService;

    // Add the public URLs here that do not need JWT validation
    private static final List<String> PUBLIC_ENDPOINTS = Arrays.asList(
            "/authenticate",
            "/registerNewUser",
            "/createRole"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        // Skip the JWT validation for public endpoints
        if (isPublicEndpoint(requestURI)) {
            filterChain.doFilter(request, response);  // Continue without JWT validation
            return;
        }

        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } else {
            System.out.println("JWT token does not start with Bearer");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = jwtService.loadUserByUsername(username);
            if (userDetails != null) {
                if (jwtUtil.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        }
        filterChain.doFilter(request, response);
    }

    // Method to check if the request URI matches a public endpoint
    private boolean isPublicEndpoint(String requestURI) {
        // Check if the URI matches any of the public static endpoints
        if (PUBLIC_ENDPOINTS.stream().anyMatch(requestURI::startsWith)) {
            return true;
        }
        // Check if the URI starts with /rest/unfold/, for dynamic paths like /rest/unfold/{categoryId}/{productId}
        return requestURI.startsWith("/rest/unfold/");
    }
}
