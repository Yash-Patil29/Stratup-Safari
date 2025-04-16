package com.example.StartupSafari.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        String redirectUrl = "/"; // fallback

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            System.out.println("User has role: " + role); // ✅ DEBUG LOG

            // ✅ Add this line for debugging
            System.out.println("Redirecting based on role: " + role);

            switch (role) {
                case "FOUNDER":
                    redirectUrl = "/founder-dashboard";
                    break;
                case "CO_FOUNDER":
                    redirectUrl = "/cofounder-dashboard";
                    break;
                case "INVESTOR":
                    redirectUrl = "/investor-dashboard";
                    break;
                default:
                    redirectUrl = "/login?error=role_not_found";
            }
        }

        response.sendRedirect(redirectUrl);
    }
}
