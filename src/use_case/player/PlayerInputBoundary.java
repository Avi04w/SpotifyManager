package use_case.player;

import data_access.Authorization;
import entity.Player;
import entity.Track;

import java.util.ArrayList;

public interface PlayerInputBoundary {
    void resume(PlayerInputData playerInputData);

    void pause(PlayerInputData playerInputData);
}
