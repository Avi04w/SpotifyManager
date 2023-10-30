package app.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class SpotifySongPlayerGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Spotify Song Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        // Create a panel for song information
        JPanel songInfoPanel = new JPanel();
        songInfoPanel.setLayout(new BorderLayout());
        JLabel songLabel = new JLabel("Now Playing: Song Title - Artist");
        songLabel.setHorizontalAlignment(JLabel.CENTER);
        songInfoPanel.add(songLabel, BorderLayout.CENTER);

        // Create a panel for the album cover
        JPanel albumCoverPanel = new JPanel();
        albumCoverPanel.setBackground(Color.BLACK);
        albumCoverPanel.setPreferredSize(new Dimension(300, 300));

        // Create a panel for playback controls at the bottom
        JPanel controlsPanel = new JPanel();

        // Create custom play button
        JButton playButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int size = Math.min(getWidth(), getHeight());

                Path2D triangle = new Path2D.Double();
                triangle.moveTo(0, 0);
                triangle.lineTo(0, size);
                triangle.lineTo(size, size / 2.0);
                triangle.closePath();

                g2d.setColor(Color.GREEN); // Play button color
                g2d.fill(triangle);
            }
        };

        playButton.setPreferredSize(new Dimension(50, 50));

        // Create custom pause button
        JButton pauseButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int size = Math.min(getWidth(), getHeight());

                Path2D pauseBars = new Path2D.Double();
                pauseBars.moveTo(size / 3.0, 0);
                pauseBars.lineTo(size / 3.0, size);
                pauseBars.moveTo(2 * size / 3.0, 0);
                pauseBars.lineTo(2 * size / 3.0, size);

                g2d.setColor(Color.RED); // Pause button color
                g2d.setStroke(new BasicStroke(15)); // Adjust line thickness
                g2d.draw(pauseBars);
            }
        };

        pauseButton.setPreferredSize(new Dimension(50, 50));

        JButton nextButton = new JButton("Next");

        controlsPanel.add(playButton);
        controlsPanel.add(pauseButton);
        controlsPanel.add(nextButton);

        frame.add(songInfoPanel, BorderLayout.NORTH);
        frame.add(albumCoverPanel, BorderLayout.CENTER);
        frame.add(controlsPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}

