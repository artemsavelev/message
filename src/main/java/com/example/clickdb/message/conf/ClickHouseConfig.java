package com.example.clickdb.message.conf;

import com.example.clickdb.message.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class ClickHouseConfig implements AutoCloseable {

    // database connection link
    private static final String DB_URL = "jdbc:clickhouse://localhost:8123/default";

    private final Connection connection;

    public ClickHouseConfig() throws SQLException {
        this.connection = DriverManager.getConnection(DB_URL);;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }


    // method for retrieving records from database
    public List<Message> get() {
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM message")) {
            ResultSet rs = pstmt.executeQuery();
            List<Message> list = new ArrayList<>();

            while (rs.next()) {
                list.add(new Message(rs.getLong("id"),
                                rs.getString("message"),
                                rs.getTimestamp("created_date").toLocalDateTime()
                        )
                );
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // method of writing data to the database
    public void send(List<Message> messages) {
        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO message (id, message, created_date) VALUES (?, ?, ?)")) {

            for (Message message : messages) {
                pstmt.setLong(1, message.getId());
                pstmt.setString(2, message.getMessage());
                pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                pstmt.addBatch();
            }
            pstmt.executeBatch();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}