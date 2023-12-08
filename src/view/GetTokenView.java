package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import data_access.Authorization;
import app.GetTokenFactory;
import interface_adapter.GetTokenViewInterface;
import use_case.get_token.GetTokenOutputBoundary;

public class GetTokenView extends JFrame implements GetTokenViewInterface {
    private final JTextField tokenField;

    /**
     * Creates a GetTokenView to facilitate the input and retrieval of access tokens.
     *
     * @param auth The Authorization object containing the Spotify API access token.
     */
    public GetTokenView(Authorization auth) {
        //Create presenter with factory
        GetTokenFactory factory = new GetTokenFactory();
        GetTokenOutputBoundary presenter = factory.createPresenter(GetTokenView.this);
        //Set up then create and add components to the frame
        //Details are like background colour and frame size are modified to look like the Login View, to add continuity to the design of the program
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

        JLabel tokenLabel = new JLabel("Access Code:");
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

    /**
     * Handles the action to be taken upon successful token retrieval.
     * Hides the GetTokenView frame.
     */
    public void success() {
        GetTokenView.this.setVisible(false);
    }

    /**
     * Handles the action to be taken upon unsuccessful token retrieval.
     * Displays an error message indicating an invalid token and prompts the user to try again.
     */
    public void failure() {
        JOptionPane.showMessageDialog(GetTokenView.this, "Invalid token. Try again.");
    }
}
