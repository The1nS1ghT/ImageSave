package com.save.save.controller;

import com.save.save.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/user/{id}/search/name")
    public String searchByName(@PathVariable("id") long idUser, String name, Model model){
        model.addAttribute("images", imageService.searchByName(idUser, name));
        return "searchNameRes";
    }

    @PostMapping("/user/{id}/search/size")
    public String searchBySize(@PathVariable("id")long idUser, long size,
                               @RequestParam("date")LocalDate date, Model model){
        model.addAttribute("images", imageService.searchBySize(idUser, size, date));
        return "searchSizeRes";
    }
}
