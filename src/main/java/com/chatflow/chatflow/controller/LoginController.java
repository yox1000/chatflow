package com.chatflow.chatflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Marks this class as a Spring MVC controller that can handle HTTP requests
@Controller
public class LoginController {

    // Handles HTTP GET requests sent to the /login URL
    // When someone visits /login in their browser, this method is called
    @GetMapping("/login")
    public String showLoginPage() {
        // Return the name of the template to render
        // Spring Boot will look for 'login.html' inside src/main/resources/templates/
        return "login";
    }

    // Handles HTTP POST requests sent to the /login URL
    // This is triggered when the login form is submitted
    @PostMapping("/login")
    public String processLogin(
            @RequestParam String username, // Gets the 'username' field from the form
            @RequestParam String password, // Gets the 'password' field from the form
            Model model) { // Used to pass data back to the view (template)

        // Simple validation: check if username and password are correct (hardcoded
        // here)
        if ("user".equals(username) && "pass".equals(password)) {
            // Login successful: return 'welcome.html' template
            return "welcome";
        } else {
            // Login failed: add an error message to the model
            model.addAttribute("error", "Invalid username or password");
            // Return back to the login page, showing the error
            return "login";
        }
    }
}
