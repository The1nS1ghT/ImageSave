package com.save.save.service;

import com.save.save.config.Config;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileStorageService {
    private final Path root;

    public FileStorageService(Config config) {
        root = Paths.get(new File("D:\\Projects\\ImageSave\\src\\main\\resources\\img").getAbsolutePath());
    }
    public String save(MultipartFile file){
        try {
            String fileName = UUID.randomUUID() + getFileExtensionWithDot(file.getOriginalFilename()).orElse("");
            String dirName = fileName.substring(0, 2);
            boolean mkdir = new File(root + "/" + dirName).mkdir();
            Files.copy(file.getInputStream(), this.root.resolve(dirName + "/" + fileName));
            return dirName + "/" + fileName;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private Optional<String> getFileExtensionWithDot(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".")));
    }

}
