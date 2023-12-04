package view;

import data_access.Authorization;
import data_access.PlayerDAO;
import entity.Artist;
import use_case.player.PlayerInputData;
import use_case.player.PlayerOutputData;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PlayerView extends JFrame implements ChangeListener{
    private JButton playButton;
    private JButton pauseButton;
    private JButton nextButton;
    private JSlider progressBar;
    private JButton previousButton; // New button for Previous
//    private JButton addToQueueButton; // New button for Add to Queue
    private JButton repeatButton;
    private JButton shuffleButton; // New button for Shuffle

    private JLabel songLabel;
    private JLabel artistLabel;
    private JLabel albumLabel;
    private JLabel songImage;
    private final PlayerInputData playerInputData;
    private final PlayerOutputData playerOutputData;
    private final PlayerDAO playerDao;
    private String deviceId;
    private boolean state;
    private String trackName;
    private String artistName;
    private String albumName;
    private String image;

    public PlayerView(Authorization token) {
        this.playerDao = new PlayerDAO();
        this.playerInputData = new PlayerInputData(token, playerDao);
        this.playerOutputData = new PlayerOutputData(token, playerDao);
        this.deviceId = playerOutputData.getAvailableDevice(token);
        this.state = playerOutputData.getShuffle(token);
        this.trackName = playerOutputData.getTrackName(token);

        ArrayList<Artist> artists = playerOutputData.getPlayer(token).getCurrentTrack().getArtists();
        StringBuilder b = new StringBuilder();
        for(Artist a : artists) {
            b.append(a.toString()).append(" ");
        }
        this.artistName = b.toString();
        this.albumName = playerOutputData.getPlayer(token).getCurrentTrack().getAlbum().getAlbumName();
        this.image = playerOutputData.getImage(token);

        setTitle("Spotify Player");
        setSize(700, 700);
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
        progressBar = new JSlider(0, 100);
//        progressBar = new JSlider(JSlider.HORIZONTAL, 0, 100);
//        songLabel = new JLabel("Now Playing: Song Title");
        songLabel = new JLabel("Now Playing: " + trackName);
        songLabel.setFont(new Font("Courier New", Font.PLAIN, 18)); // Adjust the font size
        artistLabel = new JLabel("Artist: " + artistName);
        artistLabel.setFont(new Font("Courier New", Font.PLAIN, 18)); // Adjust the font size
        albumLabel = new JLabel("Album: " + albumName);
        albumLabel.setFont(new Font("Courier New", Font.PLAIN, 18)); // Adjust the font size
        songImage = new JLabel(new ImageIcon("song_image.jpg"));
//        songImage.setPreferredSize(new Dimension(200, 200)); ▶️⏯⏹⏭⏮⏩⏪⏫🔀🔁


        //Display image
        try {
            URL url = new URL(image);
            ImageIcon icon = new ImageIcon(url);
            // Resize the image directly in the ImageIcon Constructor
            Image scaledImage = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);

            songImage.setIcon(new ImageIcon(scaledImage));
        } catch (MalformedURLException e) {
            System.out.println("No image can be displayed.");
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

        //Setting Label Components
        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.PAGE_AXIS));
        songLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        artistLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        albumLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        int labelPadding = 10; // Adjust the padding as needed
        songLabel.setBorder(BorderFactory.createEmptyBorder(labelPadding, 0, 0, 0));
        artistLabel.setBorder(BorderFactory.createEmptyBorder(labelPadding, 0, 0, 0));
        albumLabel.setBorder(BorderFactory.createEmptyBorder(labelPadding, 0, 0, 0));
        labelsPanel.add(songLabel);
        labelsPanel.add(artistLabel);
        labelsPanel.add(albumLabel);

        add(labelsPanel, BorderLayout.PAGE_START);

        add(songImage, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.PAGE_END);

        // Control volume
        progressBar.addChangeListener(this);

        // Add action listeners for buttons
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Play button action
                System.out.println("Play");
                playerInputData.resume(token, deviceId);
                openPlayerView(token);
                dispose();
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Pause button action
                System.out.println("Pause");
                playerInputData.pause(token, deviceId);
            }
        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Next button action
                if (e.getSource().equals(nextButton)){
                    System.out.println("Skip");
                    playerInputData.skip(token, deviceId);
                }
                openPlayerView(token);
                dispose();
            }
        });
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Previous button action
                System.out.println("Previous");
                playerInputData.previous(token, deviceId);
                openPlayerView(token);
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
                // repeat button action
                System.out.println("Repeat Mode");
                String repeat = playerOutputData.getRepeat(token);
                if (repeat.equals("context")){
                    playerInputData.repeat(token, deviceId, "off");
                } else {
                    playerInputData.repeat(token, deviceId, "context");
                }
                openPlayerView(token);
                dispose();
            }
        });

        shuffleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Shuffle button action
                System.out.println("Shuffle");
                state = playerOutputData.getShuffle(token);
                if (state) {
                    playerInputData.toggleShuffle(token, false, deviceId);
                    shuffleButton.setBackground(Color.GRAY);
                } else {
                    playerInputData.toggleShuffle(token, true, deviceId);
                    shuffleButton.setBackground(Color.darkGray);
                }
                openPlayerView(token);
                dispose();
            }
        });
    }
    public void openPlayerView(Authorization token) {
        PlayerView playerView = new PlayerView(token);
        playerView.setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        playerInputData.setVolume(playerInputData.getAuthorization(), progressBar.getValue(), deviceId);
    }

}
