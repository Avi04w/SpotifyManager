package app.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SpotifyPlayerGUI extends JFrame {
    private JButton playButton;
    private JButton pauseButton;
    private JButton nextButton;
    private JProgressBar progressBar;
    private JLabel songLabel;
    private JLabel songImage;
    private Timer timer;
    private int duration = 300;

    public SpotifyPlayerGUI() throws IOException {
        setTitle("Spotify Player");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add components to the frame
        JPanel controlPanel = new JPanel();
        playButton = new JButton("▶");
        pauseButton = new JButton("⏸");
        nextButton = new JButton("⏭");
        progressBar = new JProgressBar();
        songLabel = new JLabel("Now Playing: Song Title");
        URL url = new URL("https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228");
        BufferedImage image = ImageIO.read(url.openStream());
        songImage = new JLabel(new ImageIcon(image));
        songImage.setPreferredSize(new Dimension(200, 200));

        controlPanel.add(playButton);
        controlPanel.add(pauseButton);
        controlPanel.add(nextButton);
        controlPanel.add(progressBar);

        // Use GridBagLayout to center the label vertically
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(songImage, gbc);
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 0); // Add top margin
        contentPanel.add(songLabel, gbc);

        setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.PAGE_END);

        // Set up Timer to update the progress bar every second
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = progressBar.getValue();
                if (value < duration) {
                    progressBar.setValue(value + 1);
                } else {
                    // Song has ended, stop the timer or handle as needed
                    timer.stop();
                }
            }
        });

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
            SpotifyPlayerGUI playerGUI = null;
            try {
                playerGUI = new SpotifyPlayerGUI();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            playerGUI.setVisible(true);
        });
    }
}
