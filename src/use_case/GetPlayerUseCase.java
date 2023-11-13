package use_case;

import api.PlayerDB;
import api.Authorization;
import entity.Player;

public class GetPlayerUseCase {
    private final PlayerDB playerDB;
    private final Authorization authorization;

    public GetPlayerUseCase(PlayerDB playerDB, Authorization authorization) {
        this.playerDB = playerDB;
        this.authorization = authorization;
    }

    public Player getPlayer() {
        return playerDB.getPlayer(authorization);
    }
}
