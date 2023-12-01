package app.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import data_access.Authorization;
import data_access.Token;
import data_access.TokenDAO;
import use_case.get_token.GetTokenPresenter;
import use_case.get_token.GetTokenViewInterface;

public class SpotifyGetTokenGUI extends JFrame implements GetTokenViewInterface {
    private final JTextField tokenField;

    public SpotifyGetTokenGUI(Authorization auth) {
        //Create presenter and DAO
        GetTokenPresenter presenter = new GetTokenPresenter(SpotifyGetTokenGUI.this);
        TokenDAO tokenDAO = new TokenDAO(presenter);
        presenter.setDAO(tokenDAO);
        //Set up then create and add components to the frame
        //Details are like background colour and frame size are modified to look like the Login GUI, to add continuity to the design of the program
        Color spotifyGreen = new Color(30, 215, 96);
        setTitle("Get Token");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel getTokenPanel = new JPanel();
        getTokenPanel.setLayout(new GridBagLayout());
        getTokenPanel.setBackground(spotifyGreen);

        JLabel title = new JLabel("Get Token");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.BLACK);

        JLabel tokenLabel = new JLabel("Token:");
        tokenField = new JTextField(20);

        JButton enterButton = new JButton("Enter");
        enterButton.setBackground(spotifyGreen);
        enterButton.setForeground(Color.BLACK);
        enterButton.setFocusPainted(false);

        //Set up layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        getTokenPanel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        getTokenPanel.add(tokenLabel, gbc);

        gbc.gridx = 1;
        getTokenPanel.add(tokenField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        getTokenPanel.add(enterButton, gbc);

        setContentPane(getTokenPanel);

        //Add action listener for enter button
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = tokenField.getText();

                presenter.execute(code, auth);
            }
        });
    }

    public void success() {
        SpotifyGetTokenGUI.this.setVisible(false);
    }

    public void failure() {
        JOptionPane.showMessageDialog(SpotifyGetTokenGUI.this, "Invalid token. Try again.");
    }

    public static void main(String[] args) {
        Authorization token = new Token();

        System.out.println(token.getAuthURI());

        SwingUtilities.invokeLater(() -> {
            SpotifyGetTokenGUI getTokenGUI = new SpotifyGetTokenGUI(token);
            getTokenGUI.setVisible(true);
        });
    }
}