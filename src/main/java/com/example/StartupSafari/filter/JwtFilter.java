package com.example.StartupSafari.filter;

import com.example.StartupSafari.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Public endpoints (no auth needed)
        if (path.contains("/api/users/register") || path.contains("/api/users/login")) {
            chain.doFilter(request, response);
            return;
        }

        final String header = request.getHeader("Authorization");

        // Skip if no token or wrong format
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        final String token = header.substring(7); // Remove "Bearer " part

        if (jwtUtil.validateToken(token)) {
            Claims claims = jwtUtil.extractClaims(token);
            String email = claims.getSubject();
            String role = claims.get("role", String.class); // e.g., "FOUNDER"

            // Use Spring's SimpleGrantedAuthority
            User userDetails = new User(
                    email,
                    "",
                    Collections.singleton(new SimpleGrantedAuthority(role))
            );

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Set authenticated user in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}