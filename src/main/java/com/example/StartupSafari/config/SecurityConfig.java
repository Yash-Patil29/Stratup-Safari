package com.example.StartupSafari.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
<<<<<<< HEAD
    @Autowired
    private final CustomAuthenticationSuccessHandler successHandler;

    public SecurityConfig(UserDetailsService userDetailsService, CustomAuthenticationSuccessHandler successHandler) {
=======
    private final CustomAuthenticationSuccessHandler successHandler;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService,
                          CustomAuthenticationSuccessHandler successHandler) {
>>>>>>> 2fe70e86e82bc8d960c9a5a319b0ce6e9546f409
        this.userDetailsService = userDetailsService;
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/users/register", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/founder-dashboard").hasAuthority("FOUNDER")
                        .requestMatchers("/cofounder-dashboard").hasAuthority("CO_FOUNDER")
                        .requestMatchers("/investor-dashboard").hasAuthority("INVESTOR")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
<<<<<<< HEAD
                        .successHandler(successHandler)
=======
                        .successHandler(successHandler) // 👈 Role-based redirection
>>>>>>> 2fe70e86e82bc8d960c9a5a319b0ce6e9546f409
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}