package com.save.save.service;

import com.save.save.model.Image;
import com.save.save.model.Status;
import com.save.save.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service

public class ImageService {
    private final ImageRepository imageRepository;
    private final FileStorageService fileStorageService;

    @Autowired
    public ImageService(ImageRepository imageRepository, FileStorageService fileStorageService) {
        this.imageRepository = imageRepository;
        this.fileStorageService = fileStorageService;
    }

    public List<Image> getImagesByUser(long idUser) {
        return imageRepository.getAllImage(idUser);
    }


    public Image save(long id, MultipartFile file) {
        String fileNameRaw = file.getOriginalFilename();
        String fileName = fileNameRaw.substring(0, fileNameRaw.lastIndexOf('.'));
        Long fileSize = file.getSize() / 1024;
        String pathForSave = fileStorageService.save(file);
        Image image = new Image();
        image.setName(fileName);
        image.setPath(pathForSave);
        image.setIdUser(id);
        image.setSize(fileSize);
        image.setDateSave(LocalDateTime.now());
        image.setStatus(Status.ACTIVE);

        return imageRepository.insert(image);
    }

    public List<Image> searchByName(long idUser, String name) {
        return imageRepository.searchByName(idUser, name);
    }

    public List<Image> searchBySize(long idUser, long size, LocalDate date) {
        return imageRepository.searchBySizeAndDate(idUser, size, Timestamp.valueOf(date.atStartOfDay()));
    }
}
