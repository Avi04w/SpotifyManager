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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class PlayerView extends JFrame {
    private JButton playButton;
    private JButton pauseButton;
    private JButton nextButton;
    private JProgressBar progressBar;
    private JButton previousButton; // New button for Previous
//    private JButton addToQueueButton; // New button for Add to Queue
    private JButton repeatButton;
    private JButton shuffleButton; // New button for Shuffle

    private JLabel songLabel;
    private JLabel songImage;
    private PlayerViewModel playerViewModel;
    private final PlayerInputData playerInputData;
    private final PlayerOutputData playerOutputData;
    private final PlayerDAO playerDao;
    private String deviceId;
    private boolean state;
    private String trackName;
    private String image;

    public PlayerView(PlayerViewModel playerViewModel, Authorization token) {
        this.playerViewModel = playerViewModel;
        this.playerDao = new PlayerDAO();
        this.playerInputData = new PlayerInputData(token, playerDao);
        this.playerOutputData = new PlayerOutputData(token, playerDao);
        this.deviceId = playerOutputData.getAvailableDevice(token);
        this.state = playerOutputData.getShuffle(token);
        this.trackName = playerOutputData.getTrackName(token);
        this.image = playerOutputData.getImage(token);

        setTitle("Spotify Player");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Create and add components to the frame
        JPanel controlPanel = new JPanel();
        playButton = new JButton("▶");
        pauseButton = new JButton("⏸");
        nextButton = new JButton("⏭");
        previousButton = new JButton("⏮");
//        addToQueueButton = new JButton("Add to Queue");
        repeatButton = new JButton("\uD83D\uDD01");
        shuffleButton = new JButton("\uD83D\uDD00");
        progressBar = new JProgressBar();
//        songLabel = new JLabel("Now Playing: Song Title");
        songLabel = new JLabel("Now Playing: " + trackName);
        songImage = new JLabel(new ImageIcon("song_image.jpg"));
//        songImage.setPreferredSize(new Dimension(200, 200)); ▶️⏯⏹⏭⏮⏩⏪⏫🔀🔁

        try{
            URL url = new URL(image);
            InputStream is = url.openStream();
            FileOutputStream fo = new FileOutputStream(new String("image.jpg"));
            int b = 0;
            while ((b = is.read()) != -1) {
                fo.write(b);
            }
            fo.close();
            is.close();
            songImage.setIcon(new ImageIcon("image.jpg"));
            songImage.setPreferredSize(new Dimension(100, 100));
        } catch (MalformedURLException e) {
            System.out.println("No image can be displayed.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        controlPanel.add(playButton);
        controlPanel.add(pauseButton);
        controlPanel.add(nextButton);
        controlPanel.add(progressBar);
        controlPanel.add(previousButton);
//        controlPanel.add(addToQueueButton);
        controlPanel.add(repeatButton);
        controlPanel.add(shuffleButton);

        setLayout(new BorderLayout());
        add(songLabel, BorderLayout.PAGE_START);
        add(songImage, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.PAGE_END);

        // Add action listeners for buttons
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Play button action
                System.out.println("Play");
                System.out.println(deviceId);
                playerInputData.resume(token, deviceId);
                openPlayerView(playerViewModel, token);
                dispose();
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Pause button action
                System.out.println("Pause");
                System.out.println(deviceId);
                playerInputData.pause(token, deviceId);
            }
        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Next button action
                if (e.getSource().equals(nextButton)){
                    System.out.println("Skip");
//                    PlayerState playerState = PlayerView.this.playerViewModel.getPlayerState();
                    playerInputData.skip(token, deviceId);
                }
                openPlayerView(playerViewModel, token);
                dispose();
            }
        });
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Previous button action
                System.out.println("Previous");
//                PlayerState playerState = PlayerView.this.playerViewModel.getPlayerState();
                playerInputData.previous(token, deviceId);
                openPlayerView(playerViewModel, token);
                dispose();
            }

        });
//        addToQueueButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Add to Queue button action
//                System.out.println("Add to Queue");
//                PlayerState playerState = PlayerView.this.playerViewModel.getPlayerState();
//                playerInputData.getQueue(token);
//            }
//        });
        repeatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add to Queue button action
                System.out.println("Repeat Mode");
                PlayerState playerState = PlayerView.this.playerViewModel.getPlayerState();
                playerInputData.repeat(token, deviceId);
                openPlayerView(playerViewModel, token);
                dispose();
            }
        });
        shuffleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Shuffle button action
                System.out.println("Shuffle");
//                PlayerState playerState = PlayerView.this.playerViewModel.getPlayerState();
                if (state == true) {
                    playerInputData.toggleShuffle(token, false, deviceId);
                } else {
                    playerInputData.toggleShuffle(token, true, deviceId);
                }
                openPlayerView(playerViewModel, token);
                dispose();
            }
        });
    }
    public void openPlayerView(PlayerViewModel playerViewModel, Authorization token) {
        PlayerView playerView = new PlayerView(playerViewModel, token);
        playerView.setVisible(true);
    }

}
