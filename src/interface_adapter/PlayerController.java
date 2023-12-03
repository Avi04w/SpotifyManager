package interface_adapter;

import data_access.Authorization;
import entity.Player;
import entity.Track;
import use_case.player.PlayerDataAccessInterface;
import use_case.player.PlayerInputBoundary;
import use_case.player.PlayerInputData;

import java.util.ArrayList;

public class PlayerController {
    final PlayerInputBoundary playerInteractor;
    final private PlayerDataAccessInterface playerDB;
    public PlayerController(PlayerInputBoundary playerInteractor, PlayerDataAccessInterface playerDB){
        this.playerInteractor = playerInteractor;
        this.playerDB = playerDB;
    }

    public PlayerController() {
        this.playerInteractor = new PlayerInputBoundary() {
            @Override
            public void resume(PlayerInputData playerInputData) {

            }

            @Override
            public void pause(PlayerInputData playerInputData) {

            }
        };
        this.playerDB = new PlayerDataAccessInterface() {
            @Override
            public Player getPlayer(Authorization authorization) {
                return null;
            }

            @Override
            public String getAvailableDevice(Authorization authorization) {
                return null;
            }

            @Override
            public void resume(Authorization authorization, String deviceId) {

            }

            @Override
            public void pause(Authorization authorization, String deviceId) {

            }

            @Override
            public void skip(Authorization authorization, String deviceId) {

            }

            @Override
            public void previous(Authorization authorization, String deviceId) {

            }

            @Override
            public void setVolume(Authorization authorization, int volume, String deviceId) {

            }

            @Override
            public void toggleShuffle(Authorization authorization, boolean state, String deviceId) {

            }

            @Override
            public ArrayList<Track> getQueue(Authorization authorization) {
                return null;
            }
        };
    }


    public void resume(Authorization authorization, String deviceId) {
        PlayerInputData playerInputData = new PlayerInputData(authorization, playerDB);
        playerInputData.setDevice(deviceId);
        playerInteractor.resume(playerInputData);
    }

    public void pause(Authorization authorization) {
        PlayerInputData playerInputData = new PlayerInputData(authorization, playerDB);
        playerInteractor.resume(playerInputData);
    }
}
