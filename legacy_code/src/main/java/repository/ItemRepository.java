package repository;

import model.Item;
import exception.DatabaseException;
import java.util.Optional;

/**
 * Repository interface for item-related database operations.
 */
public interface ItemRepository {
    /**
     * Finds an item by its ID.
     * @param itemId The ID of the item to find
     * @return An Optional containing the item if found, empty otherwise
     * @throws DatabaseException if there is an error accessing the database
     */
    Optional<Item> findById(String itemId);

    /**
     * Saves an order item to the database.
     * @param customerId The ID of the customer placing the order
     * @param item The item being ordered
     * @throws DatabaseException if there is an error accessing the database
     */
    void saveOrderItem(String customerId, Item item);

    /**
     * Saves an order to the database.
     * @param customerId The ID of the customer placing the order
     * @param total The total price of the order
     * @throws DatabaseException if there is an error accessing the database
     */
    void saveOrder(String customerId, double total);
} 