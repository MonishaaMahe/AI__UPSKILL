package com.example.databasefactory.factory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DatabaseConnectionFactoryTest {

    @Mock
    private PostgresConnection postgresConnection;

    @Test
    void getConnection_WithValidType_ReturnsConnection() {
        // Arrange
        DatabaseConnectionFactory factory = new DatabaseConnectionFactory(postgresConnection);
        when(postgresConnection.getDatabaseType()).thenReturn("PostgreSQL");

        // Act
        DatabaseConnection connection = factory.getConnection("POSTGRES");

        // Assert
        assertNotNull(connection);
        assertEquals(postgresConnection, connection);
    }

    @Test
    void getConnection_WithInvalidType_ThrowsException() {
        // Arrange
        DatabaseConnectionFactory factory = new DatabaseConnectionFactory(postgresConnection);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.getConnection("INVALID"));
    }

    @Test
    void isConnectionTypeSupported_WithValidType_ReturnsTrue() {
        // Arrange
        DatabaseConnectionFactory factory = new DatabaseConnectionFactory(postgresConnection);

        // Act
        boolean isSupported = factory.isConnectionTypeSupported("POSTGRES");

        // Assert
        assertTrue(isSupported);
    }

    @Test
    void isConnectionTypeSupported_WithInvalidType_ReturnsFalse() {
        // Arrange
        DatabaseConnectionFactory factory = new DatabaseConnectionFactory(postgresConnection);

        // Act
        boolean isSupported = factory.isConnectionTypeSupported("INVALID");

        // Assert
        assertFalse(isSupported);
    }
} 