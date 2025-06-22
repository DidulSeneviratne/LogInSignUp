import java.sql.*;
import java.util.regex.*;

public class UserService {

    public boolean isValidEmail(String email) {
        return email.contains("@");
    }

    public boolean register(User user) {
        if (!isValidEmail(user.email)) return false;

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO new_users (first_name, last_name, email, passwd) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.firstName);
            stmt.setString(2, user.lastName);
            stmt.setString(3, user.email);
            stmt.setString(4, user.password); // Hashing recommended in production
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Registration Error: " + e.getMessage());
            return false;
        }
    }

    public boolean login(String email, String password) {
        if (!isValidEmail(email)) return false;

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM new_users WHERE email = ? AND passwd = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Login Error: " + e.getMessage());
            return false;
        }
    }

    public void showDashboard() {
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM new_users");
            System.out.println("\n--- Registered Users ---");
            while (rs.next()) {
                System.out.println(rs.getString("first_name") + " " +
                        rs.getString("last_name") + " | " +
                        rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("Dashboard Error: " + e.getMessage());
        }
    }
}

