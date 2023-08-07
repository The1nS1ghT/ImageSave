package com.save.save.controller;

import com.save.save.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/")
    public String home() {
        return "home";
    }
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        return "regist";
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestParam String name,
                                   @RequestParam String email,
                                   @RequestParam String password) throws SQLException {
        userService.register(name, email, password);
        return ResponseEntity.ok("");
    }

    @GetMapping("/login")
    public String showAuthForm(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<String> auth(@RequestParam String email,
                                       @RequestParam String password) {

        String token = userService.auth(email, password);
        return ResponseEntity.ok(token);
    }

}
