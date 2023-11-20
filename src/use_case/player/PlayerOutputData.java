package use_case.player;

import api.Authorization;
import api.PlayerDB;
import entity.Player;
import entity.Track;

import java.util.ArrayList;

public class PlayerOutputData {
    final private Authorization authorization;
    final private PlayerDB playerDB;

    public PlayerOutputData(Authorization authorization, PlayerDB playerDB) {
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
}
