import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {
        setTitle("Dashboard - Registered Users");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 255, 250));
        add(panel);

        JLabel heading = new JLabel("👥 Registered Users", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(heading, BorderLayout.NORTH);

        // Table Setup
        String[] columns = {"First Name", "Last Name", "Email"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable userTable = new JTable(tableModel);
        userTable.setRowHeight(25);
        userTable.setFont(new Font("Arial", Font.PLAIN, 14));
        userTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(userTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(new Color(0, 123, 255));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(logoutBtn, BorderLayout.SOUTH);

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        // Load users from DB
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM new_users")) {

            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                tableModel.addRow(new Object[]{firstName, lastName, email});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading users: " + e.getMessage());
        }
    }
}
