package main.java;

import model.Customer;
import model.Item;
import repository.CustomerRepository;
import repository.ItemRepository;
import service.EmailService;
import exception.DatabaseException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Service for processing customer orders.
 */
public class OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderService.class.getName());
    
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final EmailService emailService;

    public OrderService(
            CustomerRepository customerRepository,
            ItemRepository itemRepository,
            EmailService emailService) {
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.emailService = emailService;
    }

    /**
     * Processes a customer order.
     * @param customerId The ID of the customer placing the order
     * @param itemIds The list of item IDs to order
     * @throws IllegalArgumentException if customerId or itemIds are invalid
     * @throws DatabaseException if there is an error accessing the database
     */
    public void processCustomerOrder(String customerId, List<String> itemIds) {
        // Input validation
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("Customer ID cannot be null or blank");
        }
        if (itemIds == null || itemIds.isEmpty()) {
            throw new IllegalArgumentException("Item IDs cannot be null or empty");
        }
        if (itemIds.stream().anyMatch(id -> id == null || id.isBlank())) {
            throw new IllegalArgumentException("Item IDs cannot contain null or blank values");
        }

        // Find customer
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));

        // Process items
        double total = itemIds.stream()
            .map(itemId -> itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + itemId)))
            .peek(item -> {
                try {
                    itemRepository.saveOrderItem(customer.id(), item);
                } catch (DatabaseException e) {
                    LOGGER.log(Level.SEVERE, "Error saving order item: " + item.id(), e);
                    throw e;
                }
            })
            .mapToDouble(Item::price)
            .sum();

        // Save order
        try {
            itemRepository.saveOrder(customer.id(), total);
        } catch (DatabaseException e) {
            LOGGER.log(Level.SEVERE, "Error saving order for customer: " + customerId, e);
            throw e;
        }

        // Send confirmation email
        try {
            emailService.sendOrderConfirmation(customer.email(), customer.name(), total);
            LOGGER.info("Order processed successfully for customer: " + customerId);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Error sending confirmation email to: " + customer.email(), e);
            // Don't throw here as the order was successfully processed
        }
    }
}