package use_case.player;

import api.Authorization;
import api.PlayerDB;

public class GetAvailableDeviceUseCase {
    private final PlayerDB playerDB;

    public GetAvailableDeviceUseCase(PlayerDB playerDB) {
        this.playerDB = playerDB;
    }

    public void getAvailableDevice(Authorization authorization){
        playerDB.getAvailableDevice(authorization);
    }
}
