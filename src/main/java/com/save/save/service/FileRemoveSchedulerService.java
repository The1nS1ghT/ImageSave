package com.save.save.service;

import com.save.save.config.ConnectionDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
@Component
@EnableScheduling
public class FileRemoveSchedulerService {
    @Scheduled(cron = "0 0 3 * * *")
    public void RemoveImage() {
        try {
            Connection connection = ConnectionDB.getMyConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE \"Image\" set status = 'DELETED' WHERE now()::date - date_save::date > 7");
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        log.info(String.valueOf(System.currentTimeMillis()));
    }
}
