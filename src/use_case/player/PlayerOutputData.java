package use_case.player;

import data_access.Authorization;
import entity.Player;
import entity.Track;

import java.util.ArrayList;

public class PlayerOutputData {
    final private Authorization authorization;
    final private PlayerDataAccessInterface playerDB;
    public String device = "";

    public PlayerOutputData(Authorization authorization, PlayerDataAccessInterface playerDB) {
        this.authorization = authorization;
        this.playerDB = playerDB;
    }
    public String getDevice() {return device;}
    public void setDevice(String device) {this.device = device;}

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
