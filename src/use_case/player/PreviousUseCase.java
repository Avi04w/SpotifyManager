package use_case.player;

import api.Authorization;
import api.PlayerDB;

public class PreviousUseCase {
    private final PlayerDB playerDB;

    public PreviousUseCase (PlayerDB playerDB) {
        this.playerDB = playerDB;
    }

    public void previous(Authorization authorization, String deviceId){
        playerDB.previous(authorization, deviceId);
    }
}
