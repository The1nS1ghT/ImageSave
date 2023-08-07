package com.save.save.model;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class Image {
    private Long id;
    private String name;
    private String path;

    private Long size;
    private Long idUser;
    private LocalDateTime dateSave;

    private Status status;


    public Image(Long id, String name, String path, Long size, Long idUser, LocalDateTime dateSave, Status status) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.size = size;
        this.idUser = idUser;
        this.dateSave = dateSave;
        this.status = status;

    }

    public Image() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public LocalDateTime getDateSave() {
        return dateSave;
    }

    public void setDateSave(LocalDateTime dateSave) {
        this.dateSave = dateSave;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
