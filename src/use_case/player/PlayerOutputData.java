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

    public String getAvailableDevice(Authorization authorization){
        return playerDB.getAvailableDevice(authorization);
    }

    public Player getPlayer(Authorization authorization) {
        return playerDB.getPlayer(authorization);
    }

    public ArrayList<Track> getQueue(Authorization authorization){
        return playerDB.getQueue(authorization);
    }
    public String getTrackName(Authorization authorization) {
        Track currentTrack = this.getQueue(authorization).get(0);
        return currentTrack.getName();
    }
    public String getImage(Authorization authorization) {
        Track currentTrack = this.getQueue(authorization).get(0);
        Album currentAlbum = currentTrack.getAlbum();
        return currentAlbum.getImage();
    }
}
