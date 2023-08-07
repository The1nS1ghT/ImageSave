package com.save.save.repository;

import com.save.save.config.ConnectionDB;
import com.save.save.model.Image;
import com.save.save.model.Status;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ImageRepository {
    public List<Image> getAllImage(long idUser) {
        try {
            List<Image> image = new ArrayList<>();
            Connection connection = ConnectionDB.getMyConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT name_image FROM \"Image\" WHERE id_user =?");
            preparedStatement.setLong(1, idUser);
            ResultSet res = preparedStatement.executeQuery();
            connection.close();
            while (res.next()) {
                Image img = new Image();
                img.setName(res.getString("name_image"));
                image.add(img);
            }
            return image;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Image insert(Image image) {
        try {
            Connection connection = ConnectionDB.getMyConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO \"Image\" (name_image, path_image  ,id_user, size_image, date_save, status) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, image.getName());
            preparedStatement.setString(2, image.getPath());
            preparedStatement.setLong(3, image.getIdUser());
            preparedStatement.setLong(4, image.getSize());
            preparedStatement.setObject(5, image.getDateSave());
            preparedStatement.setString(6, String.valueOf(image.getStatus()));
            preparedStatement.executeUpdate();
            return image;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Image> searchByName(long idUser, String name) {
        try {
            List<Image> imageList = new ArrayList<>();
            Connection connection = ConnectionDB.getMyConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT id_image, name_image FROM \"Image\" WHERE " +
                            "status = 'ACTIVE' " +
                            "AND id_user =? " +
                            "AND name_image LIKE ?");
            preparedStatement.setLong(1, idUser);
            preparedStatement.setString(2, name + "%");
            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                Image image = new Image();
                image.setId(res.getLong("id_image"));
                image.setName(res.getString("name_image"));
                imageList.add(image);
            }
            return imageList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Image> searchBySize(long idUser, long size, Timestamp date) {
        try {
            List<Image> imageList = new ArrayList<>();
            Connection connection = ConnectionDB.getMyConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT id_image, name_image FROM \"Image\" WHERE " +
                            "status = 'ACTIVE' " +
                            "AND id_user =? " +
                            "AND size_image > ? " +
                            "AND date_save::date = ?");
            preparedStatement.setLong(1, idUser);
            preparedStatement.setLong(2, size);
            preparedStatement.setTimestamp(3, date);
            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                Image image = new Image();
                image.setId(res.getLong("id_image"));
                image.setName(res.getString("name_image"));
                imageList.add(image);
            }
            return imageList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
