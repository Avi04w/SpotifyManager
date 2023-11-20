package use_case.player;

import api.Authorization;
import api.PlayerDB;

public class GetAvailableDeviceUseCase {
    private final PlayerDB playerDB;

    public GetAvailableDeviceUseCase(PlayerDB playerDB) {
        this.playerDB = playerDB;
    }

    public String getAvailableDevice(Authorization authorization){
        return playerDB.getAvailableDevice(authorization);
    }
}
