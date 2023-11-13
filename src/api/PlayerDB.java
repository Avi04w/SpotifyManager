package api;

import entity.Player;

public interface PlayerDB {
    Player getPlayer(Authorization authorization);
}
