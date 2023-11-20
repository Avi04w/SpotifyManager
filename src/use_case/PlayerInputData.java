package use_case;

import api.Authorization;
import api.PlayerDB;
import entity.Player;
import entity.Track;

import java.util.ArrayList;

public class PlayerInputData {
    final private Authorization authorization;
    final private PlayerDB playerDB;

    public PlayerInputData(Authorization authorization, PlayerDB playerDB) {
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

    public void pause(Authorization authorization, String deviceId){
        playerDB.pause(authorization, deviceId);
    }

    public void previous(Authorization authorization, String deviceId){
        playerDB.previous(authorization, deviceId);
    }

    public void resume(Authorization authorization, String deviceId){
        playerDB.resume(authorization, deviceId);
    }

    public void setVolume(Authorization authorization, int volume, String deviceId){
        playerDB.setVolume(authorization, volume, deviceId);
    }

    public void skip(Authorization authorization, String deviceId){
        playerDB.skip(authorization, deviceId);
    }

    public void toggleShuffle(Authorization authorization, boolean state, String deviceId){
        playerDB.toggleShuffle(authorization, state, deviceId);
    }
}
