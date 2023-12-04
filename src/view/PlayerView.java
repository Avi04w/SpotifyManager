package view;

import interface_adapter.PlayerController;
import interface_adapter.PlayerState;
import interface_adapter.PlayerViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerView extends JFrame {
    private JButton playButton;
    private JButton pauseButton;
    private JButton nextButton;
    private JProgressBar progressBar;
    private JLabel songLabel;
    private JLabel songImage;
    private final PlayerController playerController;
    private final PlayerViewModel playerViewModel;

    public PlayerView(PlayerController playerController, PlayerViewModel playerViewModel) {
        this.playerController = playerController;
        this.playerViewModel = playerViewModel;
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
                if (e.getSource().equals(playButton)) {
                    PlayerState playerState = PlayerView.this.playerViewModel.getPlayerState();
                    PlayerView.this.playerController.resume(playerState.getAuthorization(), playerState.getDeviceId());
                }
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pause button action
                if (e.getSource().equals(playButton)) {
                    PlayerState playerState = PlayerView.this.playerViewModel.getPlayerState();
                    PlayerView.this.playerController.pause(playerState.getAuthorization());
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Next button action
                if (e.getSource().equals(nextButton)){
                    PlayerState playerState = PlayerView.this.playerViewModel.getPlayerState();
                    PlayerView.this.playerController.skip(playerState.getAuthorization());
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlayerView playerGUI = new PlayerView(new PlayerController(), new PlayerViewModel());
            playerGUI.setVisible(true);
        });
    }
}
