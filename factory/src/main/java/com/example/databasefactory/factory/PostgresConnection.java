package com.example.databasefactory.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@Component
public class PostgresConnection implements DatabaseConnection {
    
    private Connection connection;
    
    @Value("${spring.datasource.url}")
    private String url;
    
    @Value("${spring.datasource.username}")
    private String username;
    
    @Value("${spring.datasource.password}")
    private String password;

    @Override
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
                log.info("PostgreSQL connection established successfully");
            }
            return connection;
        } catch (SQLException e) {
            log.error("Error connecting to PostgreSQL database", e);
            throw new RuntimeException("Failed to connect to PostgreSQL database", e);
        }
    }

    @Override
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                log.info("PostgreSQL connection closed successfully");
            }
        } catch (SQLException e) {
            log.error("Error closing PostgreSQL connection", e);
            throw new RuntimeException("Failed to close PostgreSQL connection", e);
        }
    }

    @Override
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public String getDatabaseType() {
        return "PostgreSQL";
    }
} 