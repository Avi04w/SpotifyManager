package use_case.player;

import data_access.Authorization;

public class PlayerInputData {
    final private Authorization authorization;
    final private PlayerDataAccessInterface playerDB;
    private String deviceId = "";

    public PlayerInputData(Authorization authorization, PlayerDataAccessInterface playerDB) {
        this.authorization = authorization;
        this.playerDB = playerDB;
    }

    public Authorization getAuthorization() {return authorization;}
    public void setDevice(String deviceId) {this.deviceId = deviceId;}
    public String getDevice() {return deviceId;}

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
