package com.example.StartupSafari.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

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
        String redirectURL = request.getContextPath();

        for (GrantedAuthority auth : authorities) {
            switch (auth.getAuthority()) {
                case "FOUNDER":
                    redirectURL += "/founder-dashboard";
                    break;
                case "CO_FOUNDER":
                    redirectURL += "/cofounder-dashboard";
                    break;
                case "INVESTOR":
                    redirectURL += "/investor-dashboard";
                    break;
            }
        }

        response.sendRedirect(redirectURL);
    }
}

