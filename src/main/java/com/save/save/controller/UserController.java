package com.save.save.controller;

import com.save.save.service.ImageService;
import com.save.save.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final ImageService imageService;


    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/user/{id}")
    public String getImagesByUser(@PathVariable("id") long idUser, Model model) {
        model.addAttribute("user", userService.getUserById(idUser));
        model.addAttribute("images", imageService.getImagesByUser(idUser));
        return "user";
    }

    @PostMapping("/user/{id}/new")
    public String saveImage(@PathVariable("id") long id, MultipartFile file) {
        imageService.save(id, file);
        //return ResponseEntity.ok("");
        return "redirect:/users";
    }

}
