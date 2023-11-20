package use_case.player;

import api.Authorization;
import api.PlayerDB;

public class SetVolumeUseCase {
    private final PlayerDB playerDB;

    public SetVolumeUseCase(PlayerDB playerDB) {
        this.playerDB = playerDB;
    }

    public void setVolume(Authorization authorization, int volume, String deviceId){
        playerDB.setVolume(authorization, volume, deviceId);
    }
}
