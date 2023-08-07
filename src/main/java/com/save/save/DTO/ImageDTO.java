package com.save.save.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class ImageDTO {
    public String name;
    public String path;
    public Long size;
    public LocalDateTime date;
    public MultipartFile file;
}
