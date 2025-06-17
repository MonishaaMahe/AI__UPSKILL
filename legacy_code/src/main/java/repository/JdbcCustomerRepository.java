package repository;

import model.Customer;
import exception.DatabaseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;

/**
 * JDBC implementation of CustomerRepository.
 */
public class JdbcCustomerRepository implements CustomerRepository {
    private final DataSource dataSource;

    public JdbcCustomerRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Customer> findById(String customerId) {
        String sql = "SELECT id, name, email FROM customers WHERE id = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, customerId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Customer(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email")
                    ));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error finding customer by ID: " + customerId, e);
        }
    }
} 