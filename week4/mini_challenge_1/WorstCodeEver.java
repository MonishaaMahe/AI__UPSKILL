import java.util.Scanner;

public class WorstCodeEver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username:");
        String user = scanner.nextLine();
        System.out.println("Enter amount:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        System.out.println("Enter item names (comma-separated):");
        String items = scanner.nextLine();
        System.out.println("Enter quantities (comma-separated):");
        String qty = scanner.nextLine();
        System.out.println("Are you VIP? true/false:");
        boolean vip = scanner.nextBoolean();

        new WorstCodeEver().doEverything(user, amount, items, qty, vip);
    }

    public void doEverything(String user, double amount, String items, String qty, boolean vip) {
        if (user == null || user.length() < 3 || amount <= 0 || items.isEmpty() || qty.isEmpty()) {
            System.out.println("Invalid input.");
            return;
        }

        String[] itemArray = items.split(",");
        String[] qtyArray = qty.split(",");
        if (itemArray.length != qtyArray.length) {
            System.out.println("Mismatch between items and quantities");
            return;
        }

        double discount = vip ? 0.2 : 0;
        double total = amount - (amount * discount);

        System.out.println("Order for " + user + ":");
        for (int i = 0; i < itemArray.length; i++) {
            System.out.println("Item: " + itemArray[i] + " Qty: " + qtyArray[i]);
        }

        System.out.println("VIP: " + vip);
        System.out.println("Total after discount: " + total);
        System.out.println("Order saved to imaginary database (not really)");
    }
}
