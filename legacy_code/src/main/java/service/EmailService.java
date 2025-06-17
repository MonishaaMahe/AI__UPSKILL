package service;

/**
 * Service for sending emails.
 */
public interface EmailService {
    /**
     * Sends an order confirmation email.
     * @param email The recipient's email address
     * @param name The recipient's name
     * @param total The total order amount
     * @throws RuntimeException if there is an error sending the email
     */
    void sendOrderConfirmation(String email, String name, double total);
} 