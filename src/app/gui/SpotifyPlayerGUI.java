package app.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpotifyPlayerGUI extends JFrame {
    private JButton playButton;
    private JButton pauseButton;
    private JButton nextButton;
    private JProgressBar progressBar;
    private JLabel songLabel;
    private JLabel songImage;

    public SpotifyPlayerGUI() {
        setTitle("Spotify Player");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add components to the frame
        JPanel controlPanel = new JPanel();
        playButton = new JButton("▶");
        pauseButton = new JButton("⏸");
        nextButton = new JButton("⏭");
        progressBar = new JProgressBar();
        songLabel = new JLabel("Now Playing: Song Title");
        songImage = new JLabel(new ImageIcon("song_image.jpg"));
        songImage.setPreferredSize(new Dimension(200, 200));

        controlPanel.add(playButton);
        controlPanel.add(pauseButton);
        controlPanel.add(nextButton);
        controlPanel.add(progressBar);

        setLayout(new BorderLayout());
        add(songLabel, BorderLayout.PAGE_START);
        add(songImage, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.PAGE_END);

        // Add action listeners for buttons
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Play button action
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pause button action
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Next button action
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SpotifyPlayerGUI playerGUI = new SpotifyPlayerGUI();
            playerGUI.setVisible(true);
        });
    }
}