package use_case.player;

import api.Authorization;
import api.PlayerDB;

public class ResumeUseCase {
    private final PlayerDB playerDB;

    public ResumeUseCase(PlayerDB playerDB) {
        this.playerDB = playerDB;
    }

    public void resume(Authorization authorization, String deviceId){
        playerDB.resume(authorization, deviceId);
    }
}
