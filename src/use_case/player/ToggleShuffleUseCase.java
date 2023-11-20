package use_case.player;

import api.Authorization;
import api.PlayerDB;

public class ToggleShuffleUseCase {
    private final PlayerDB playerDB;

    public ToggleShuffleUseCase (PlayerDB playerDB) {
        this.playerDB = playerDB;
    }

    public void toggleShuffle(Authorization authorization, boolean state, String deviceId){
        playerDB.toggleShuffle(authorization, state, deviceId);
    }
}