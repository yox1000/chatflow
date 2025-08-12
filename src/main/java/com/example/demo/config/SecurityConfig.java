package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// Marks this class as a configuration class for Spring
@Configuration
public class SecurityConfig {

    // Defines the users and their passwords in memory
    @Bean
    public UserDetailsService userDetailsService() {
        // Create a user with username "user1" and password "pass1" with role USER
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("user1")
                .password("pass1")
                .roles("USER")
                .build();

        // Create another user with username "user2" and password "pass2"
        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("user2")
                .password("pass2")
                .roles("USER")
                .build();

        // Return an in-memory user manager with these two users
        return new InMemoryUserDetailsManager(user1, user2);
    }

    // Configure the HTTP security, specifying what URLs require authentication and
    // how login/logout work
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Require authentication for all requests
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated())
                // Configure form login
                .formLogin(form -> form
                        .loginPage("/login") // Use a custom login page at /login URL (you must provide this page)
                        .permitAll() // Allow anyone to see the login page without being authenticated
                )
                // Enable logout support and allow everyone to access it
                .logout(logout -> logout.permitAll());

        // Build the security filter chain object and return it
        return http.build();
    }
}
