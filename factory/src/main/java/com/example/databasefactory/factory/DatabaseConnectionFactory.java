package com.example.databasefactory.factory;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DatabaseConnectionFactory {
    
    private final Map<String, DatabaseConnection> connections;
    
    public DatabaseConnectionFactory(PostgresConnection postgresConnection) {
        connections = new ConcurrentHashMap<>();
        connections.put("POSTGRES", postgresConnection);
    }
    
    public DatabaseConnection getConnection(String type) {
        DatabaseConnection connection = connections.get(type.toUpperCase());
        if (connection == null) {
            throw new IllegalArgumentException("Unsupported database type: " + type);
        }
        return connection;
    }
    
    public boolean isConnectionTypeSupported(String type) {
        return connections.containsKey(type.toUpperCase());
    }
} 