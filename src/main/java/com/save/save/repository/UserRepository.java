package com.save.save.repository;

import com.save.save.config.ConnectionDB;
import com.save.save.model.Role;
import com.save.save.model.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    public String insertUser(User user) throws SQLException {
        Connection connection = ConnectionDB.getMyConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO \"User\" (name, email ,password, created_at) VALUES (?,?,?,?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setObject(4, user.getCreatedAt());
        preparedStatement.executeUpdate();
        connection.close();
        return null;
    }

    public User findById(User user, long idUser) {
        try {
            Connection connection = ConnectionDB.getMyConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT id_user, name, email FROM \"User\" WHERE id_user =?");
            preparedStatement.setLong(1, idUser);
            ResultSet res = preparedStatement.executeQuery();

            if (res.next()) {
                user.setId(res.getLong("id_user"));
                user.setName(res.getString("name"));
                user.setEmail(res.getString("email"));

                connection.close();
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findByEmail(String email) {
        try {
            Connection connection = ConnectionDB.getMyConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT id_user, name, email, password, created_at, role FROM \"User\" WHERE email =?");
            preparedStatement.setString(1, email);
            ResultSet res = preparedStatement.executeQuery();
            connection.close();

            if (res.next()) {
                User user = new User();
                user.setId(res.getLong("id_user"));
                user.setName(res.getString("name"));
                user.setEmail(res.getString("email"));
                user.setPassword(res.getString("password"));
                user.setCreatedAt(res.getTimestamp("created_at").toLocalDateTime());
                user.setRole(Role.USER);
                return user;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getUsers() {
        try {
            List<User> users = new ArrayList<>();
            Connection connection = ConnectionDB.getMyConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT id_user, name, email FROM \"User\"");
            ResultSet res = preparedStatement.executeQuery();
            connection.close();

            while (res.next()) {
                User user = new User();
                user.setId(res.getLong("id_user"));
                user.setName(res.getString("name"));
                user.setEmail(res.getString("email"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
