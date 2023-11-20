package use_case.player;

import api.Authorization;
import api.PlayerDB;

public class PauseUseCase {
    private final PlayerDB playerDB;

    public PauseUseCase(PlayerDB playerDB) {
        this.playerDB = playerDB;
    }

    public void pause(Authorization authorization, String deviceId){
        playerDB.pause(authorization, deviceId);
    }
}
