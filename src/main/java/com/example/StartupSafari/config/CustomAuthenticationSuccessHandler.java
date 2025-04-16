package com.example.StartupSafari.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();

            if (role.equals("FOUNDER")) {
                response.sendRedirect("/founder-dashboard");
                return;
            } else if (role.equals("CO_FOUNDER")) {
                response.sendRedirect("/cofounder-dashboard");
                return;
            } else if (role.equals("INVESTOR")) {
                response.sendRedirect("/investor-dashboard");
                return;
            }
        }

        response.sendRedirect("/login?error=role-not-found");
    }
}
