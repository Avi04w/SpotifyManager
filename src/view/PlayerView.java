package view;

import data_access.Authorization;
import data_access.PlayerDAO;
import interface_adapter.PlayerState;
import interface_adapter.PlayerViewModel;
import use_case.player.PlayerDataAccessInterface;
import use_case.player.PlayerInputData;
import use_case.player.PlayerOutputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PlayerView extends JFrame {
    private JButton playButton;
    private JButton pauseButton;
    private JButton nextButton;
    private JProgressBar progressBar;
    private JLabel songLabel;
    private JLabel songImage;
    private PlayerViewModel playerViewModel;
    private final PlayerInputData playerInputData;
    private final PlayerOutputData playerOutputData;
    private final PlayerDAO playerDao;
    private String deviceId;

    public PlayerView(PlayerViewModel playerViewModel, Authorization token) {
        this.playerViewModel = playerViewModel;
        this.playerDao = new PlayerDAO();
        this.playerInputData = new PlayerInputData(token, playerDao);
        this.playerOutputData = new PlayerOutputData(token, playerDao);

        deviceId = playerOutputData.getDevice();

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
                deviceId = playerDao.getAvailableDevice(token);
                playerDao.resume(token, deviceId);
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pause button action
                deviceId = playerDao.getAvailableDevice(token);
                playerDao.pause(token, deviceId);
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Next button action
                if (e.getSource().equals(nextButton)){
                    PlayerState playerState = PlayerView.this.playerViewModel.getPlayerState();
                    playerInputData.skip(token, deviceId);
                }
            }
        });
    }
}
