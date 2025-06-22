import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private JTextField firstNameField, lastNameField, emailField;
    private JPasswordField passwordField;
    private UserService userService = new UserService();

    public RegisterFrame() {
        setTitle("Register Account");
        setSize(400, 360);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(230, 240, 255));
        add(panel);

        JLabel heading = new JLabel("Create Account");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setBounds(120, 10, 200, 30);
        panel.add(heading);

        JLabel fnLabel = new JLabel("First Name:");
        fnLabel.setBounds(40, 60, 100, 25);
        panel.add(fnLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(140, 60, 200, 25);
        panel.add(firstNameField);

        JLabel lnLabel = new JLabel("Last Name:");
        lnLabel.setBounds(40, 95, 100, 25);
        panel.add(lnLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(140, 95, 200, 25);
        panel.add(lastNameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(40, 130, 100, 25);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(140, 130, 200, 25);
        panel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(40, 165, 100, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 165, 200, 25);
        panel.add(passwordField);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(60, 220, 120, 30);
        registerBtn.setBackground(new Color(0, 150, 136));
        registerBtn.setForeground(Color.WHITE);
        panel.add(registerBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(200, 220, 120, 30);
        backBtn.setBackground(new Color(220, 53, 69));
        backBtn.setForeground(Color.WHITE);
        panel.add(backBtn);

        registerBtn.addActionListener(e -> {
            User user = new User(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    emailField.getText(),
                    new String(passwordField.getPassword())
            );

            if (userService.register(user)) {
                JOptionPane.showMessageDialog(null, "Registered Successfully!");
                dispose();
                new LoginFrame().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Registration Failed.");
            }
        });

        backBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }
}
