package interface_adapter;

import data_access.Authorization;
import use_case.player.PlayerInputBoundary;
import use_case.player.PlayerInputData;

public class PlayerController {
    final PlayerInputBoundary playerInteractor;
    public PlayerController(PlayerInputBoundary playerInteractor){
        this.playerInteractor = playerInteractor;
    }
//    public void resume(Authorization authorization, String deviceId){
//        PlayerInputData playerInputData = new PlayerInputData(authorization, deviceId);
//        playerInteractor.resume(playerInputData);
//    }
    public void resume(){
        PlayerInputData playerInputData = new PlayerInputData();
        playerInteractor.resume(playerInputData);
    }

}
