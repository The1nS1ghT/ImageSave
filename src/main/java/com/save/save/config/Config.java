package com.save.save.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Value("${file-storage-path}")
    public String fileStoragePath;

    public String getFileStoragePath() {
        return fileStoragePath;
    }
}
