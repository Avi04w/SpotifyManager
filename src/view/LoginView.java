package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginView() {
        // Set up the frame
        setTitle("Spotify Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create main panel with Spotify green background
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(30, 215, 96)); // Spotify Green

        // Create components
        JLabel title = new JLabel("Spotify Login");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.BLACK);

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(30, 215, 96)); // Spotify Green
        loginButton.setForeground(Color.BLACK);
        loginButton.setFocusPainted(false);

        // Set up layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        mainPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(loginButton, gbc);

        // Set the main panel as the content pane of the JFrame
        setContentPane(mainPanel);

        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your authentication logic here
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

                // Validate username and password
                if ("yourUsername".equals(username) && "yourPassword".equals(new String(password))) {
                    JOptionPane.showMessageDialog(LoginView.this, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(LoginView.this, "Invalid username or password. Try again.");
                }

                // Clear fields after login attempt
                usernameField.setText("");
                passwordField.setText("");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }
}
