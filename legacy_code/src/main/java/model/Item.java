package model;

/**
 * Represents an item in the system.
 * @param id The unique identifier of the item
 * @param name The item's name
 * @param price The item's price
 */
public record Item(
    String id,
    String name,
    double price
) {
    /**
     * Validates the item data.
     * @throws IllegalArgumentException if any field is invalid
     */
    public Item {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Item ID cannot be null or blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Item name cannot be null or blank");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
} 