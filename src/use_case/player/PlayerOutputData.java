package use_case.player;

import data_access.Authorization;
import entity.Album;
import entity.Player;
import entity.Track;

import java.util.ArrayList;

public class PlayerOutputData {
    final private Authorization authorization;
    final private PlayerDataAccessInterface playerDB;

    public PlayerOutputData(Authorization authorization, PlayerDataAccessInterface playerDB) {
        this.authorization = authorization;
        this.playerDB = playerDB;
    }

    /**
     * Retrieves the available device ID associated with the provided Authorization.
     *
     * @param authorization The Authorization object containing the Spotify API access token.
     * @return A string representing the available device ID.
     */
    public String getAvailableDevice(Authorization authorization) {
        return playerDB.getAvailableDevice(authorization);
    }

    /**
     * Retrieves the Player object associated with the provided Authorization.
     *
     * @param authorization The Authorization object containing the Spotify API access token.
     * @return The Player object associated with the provided Authorization.
     */
    public Player getPlayer(Authorization authorization) {
        return playerDB.getPlayer(authorization);
    }

    /**
     * Retrieves the queue of tracks associated with the provided Authorization.
     *
     * @param authorization The Authorization object containing the Spotify API access token.
     * @return An ArrayList of Track objects representing the track queue.
     */
    public ArrayList<Track> getQueue(Authorization authorization) {
        return playerDB.getQueue(authorization);
    }

    /**
     * Retrieves the currently playing track associated with the provided Authorization.
     *
     * @param authorization The Authorization object containing the Spotify API access token.
     * @return The currently playing Track object.
     */
    public Track getCurrentlyPlaying(Authorization authorization) {
        return playerDB.getCurrentlyPlaying(authorization);
    }

    /**
     * Retrieves the name of the currently playing track associated with the provided Authorization.
     *
     * @param authorization The Authorization object containing the Spotify API access token.
     * @return A string representing the name of the currently playing track.
     */
    public String getTrackName(Authorization authorization) {
        Track currentTrack = this.getCurrentlyPlaying(authorization);
        return currentTrack.getName();
    }

    /**
     * Retrieves the image URL of the currently playing track's album associated with the provided Authorization.
     *
     * @param authorization The Authorization object containing the Spotify API access token.
     * @return A string representing the image URL of the currently playing track's album.
     */
    public String getImage(Authorization authorization) {
        Track currentTrack = this.getCurrentlyPlaying(authorization);
        Album currentAlbum = currentTrack.getAlbum();
        return currentAlbum.getImage();
    }

    /**
     * Retrieves the shuffle status of the player associated with the provided Authorization.
     *
     * @param authorization The Authorization object containing the Spotify API access token.
     * @return A boolean indicating whether shuffle is enabled.
     */
    public boolean getShuffle(Authorization authorization) {
        return playerDB.getPlayer(authorization).isShuffle();
    }

    /**
     * Retrieves the repeat mode of the player associated with the provided Authorization.
     *
     * @param authorization The Authorization object containing the Spotify API access token.
     * @return A string representing the repeat mode (e.g., "track", "context", "off").
     */
    public String getRepeat(Authorization authorization) {
        return playerDB.getPlayer(authorization).getRepeat();
    }

}
