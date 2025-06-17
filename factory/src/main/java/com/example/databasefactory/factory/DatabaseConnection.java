package com.example.databasefactory.factory;

import java.sql.Connection;

public interface DatabaseConnection {
    Connection getConnection();
    void closeConnection();
    boolean isConnected();
    String getDatabaseType();
} 