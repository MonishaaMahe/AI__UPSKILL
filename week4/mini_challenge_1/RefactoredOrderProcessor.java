import java.util.Scanner;
import java.util.logging.Logger;

public class RefactoredOrderProcessor {
    private static final Logger logger = Logger.getLogger(RefactoredOrderProcessor.class.getName());
    private static final double VIP_DISCOUNT = 0.2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            String user = promptUser(scanner, "Enter your username:");
            double amount = promptAmount(scanner);
            String[] items = promptArray(scanner, "Enter item names (comma-separated):");
            int[] qty = promptIntArray(scanner, "Enter quantities (comma-separated):", items.length);
            boolean vip = promptVip(scanner);

            RefactoredOrderProcessor processor = new RefactoredOrderProcessor();
            processor.processOrder(user, amount, items, qty, vip);
        } catch (IllegalArgumentException e) {
            logger.severe(e.getMessage());
        }
    }

    public void processOrder(String user, double amount, String[] items, int[] qty, boolean vip) {
        validateInput(user, amount, items, qty);
        double total = calculateTotal(amount, vip);
        printOrder(user, items, qty, vip, total);
        logger.info("Order saved to imaginary database (not really)");
    }

    private static String promptUser(Scanner scanner, String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();
        if (input == null || input.length() < 3) {
            throw new IllegalArgumentException("Invalid username.");
        }
        return input;
    }

    private static double promptAmount(Scanner scanner) {
        System.out.println("Enter amount:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        return amount;
    }

    private static String[] promptArray(Scanner scanner, String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty.");
        }
        return input.split(",");
    }

    private static int[] promptIntArray(Scanner scanner, String prompt, int expectedLength) {
        System.out.println(prompt);
        String input = scanner.nextLine();
        String[] parts = input.split(",");
        if (parts.length != expectedLength) {
            throw new IllegalArgumentException("Mismatch between items and quantities.");
        }
        int[] result = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            try {
                result[i] = Integer.parseInt(parts[i].trim());
                if (result[i] < 1) {
                    throw new IllegalArgumentException("Quantity must be at least 1 for all items.");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Quantities must be integers.");
            }
        }
        return result;
    }

    private static boolean promptVip(Scanner scanner) {
        System.out.println("Are you VIP? true/false:");
        return scanner.nextBoolean();
    }

    private void validateInput(String user, double amount, String[] items, int[] qty) {
        if (user == null || user.length() < 3 || amount <= 0 || items == null || qty == null || items.length != qty.length) {
            throw new IllegalArgumentException("Invalid input.");
        }
    }

    private double calculateTotal(double amount, boolean vip) {
        double discount = vip ? VIP_DISCOUNT : 0;
        return amount - (amount * discount);
    }

    private void printOrder(String user, String[] items, int[] qty, boolean vip, double total) {
        logger.info("Order for " + user + ":");
        for (int i = 0; i < items.length; i++) {
            logger.info("Item: " + items[i].trim() + " Qty: " + qty[i]);
        }
        logger.info("VIP: " + vip);
        logger.info("Total after discount: " + total);
    }
} 