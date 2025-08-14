package com.chatflow.chatflow.config;

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
                .authorizeHttpRequests(auth -> auth
                // Permit all users to access the login page and resources needed
                .requestMatchers("/login", "/login/**").permitAll()
                // All other requests require authentication
                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
        }

}
