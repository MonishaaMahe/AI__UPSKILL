import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class RefactoredSecureLoginApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Hardcoded DB credentials (for demonstration only)
        String dbUrl = "jdbc:mysql://localhost:3306/testdb";
        String dbUser = "root";
        String dbPass = "root123";

        System.out.println("Enter username:");
        String username = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        // Use PreparedStatement to prevent SQL Injection
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try {
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid credentials.");
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 