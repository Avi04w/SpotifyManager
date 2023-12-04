package view;

import data_access.Authorization;
import data_access.Token;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class LoginView extends JFrame {

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
                Authorization token = new Token();
                URI uri;
                try {
                    uri = new URI(token.getAuthURI());
                } catch (URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.browse(uri);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    System.out.println("Desktop is not supported.");
                }

                openGetTokenView(token);

                dispose();
            }
        });
    }

    private void openGetTokenView(Authorization token) {
        GetTokenView getTokenView = new GetTokenView(token);
        getTokenView.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
