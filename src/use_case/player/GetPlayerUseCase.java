package use_case.player;

import api.PlayerDB;
import api.Authorization;
import entity.Player;

public class GetPlayerUseCase {
    private final PlayerDB playerDB;

    public GetPlayerUseCase(PlayerDB playerDB) {
        this.playerDB = playerDB;
    }

    public Player getPlayer(Authorization authorization) {
        return playerDB.getPlayer(authorization);
    }
}
