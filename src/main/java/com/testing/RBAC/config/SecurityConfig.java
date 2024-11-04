package com.testing.RBAC.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()  // Publicly accessible URLs
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // Admin URLs, only accessible by ADMIN
                        .requestMatchers("/view/**").hasAnyRole("USER", "ADMIN")  // User URLs, accessible by USER and ADMIN
                        .anyRequest().authenticated()  // All other requests require authentication
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Custom login page
                        .defaultSuccessUrl("/home", true)  // Redirect after successful login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")  // Redirect after logout
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Define BCryptPasswordEncoder as the password encoder
    }
}
