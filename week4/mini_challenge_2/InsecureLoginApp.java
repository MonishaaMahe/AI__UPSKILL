import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class InsecureLoginApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Hardcoded DB credentials (🔴 Security Issue #1)
        String dbUrl = "jdbc:mysql://localhost:3306/testdb";
        String dbUser = "root";
        String dbPass = "root123";

        System.out.println("Enter username:");
        String username = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        // SQL Injection vulnerability (🔴 Security Issue #2)
        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";

        try {
            // No input validation or hashing (🔴 Security Issue #3 and #4)
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                System.out.println("Login successful. Welcome, " + rs.getString("username") + "!");
            } else {
                System.out.println("Login failed.");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace(); // 🔴 Security Issue #5: Leaks stack trace to user
        }
    }
}
