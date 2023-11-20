package use_case.player;

import api.Authorization;
import api.PlayerDB;
import entity.Track;

import java.util.ArrayList;

public class GetQueueUseCase {
    private final PlayerDB playerDB;

    public GetQueueUseCase(PlayerDB playerDB, Authorization authorization) {
        this.playerDB = playerDB;
    }

    public ArrayList<Track> getQueue(Authorization authorization){
        return playerDB.getQueue(authorization);
    }
}
