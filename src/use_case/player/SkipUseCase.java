package use_case.player;

import api.Authorization;
import api.PlayerDB;

public class SkipUseCase {
    private final PlayerDB playerDB;

    public SkipUseCase(PlayerDB playerDB) {
        this.playerDB = playerDB;
    }

    public void skip(Authorization authorization, String deviceId){
        playerDB.skip(authorization, deviceId);
    }
}
