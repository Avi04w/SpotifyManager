package use_case.player;

import data_access.Authorization;
import entity.Player;
import entity.Track;

import java.util.ArrayList;

public interface PlayerDataAccessInterface {
    Player getPlayer(Authorization authorization);
    String getAvailableDevice(Authorization authorization);
    void resume(Authorization authorization, String deviceId);
    void pause(Authorization authorization, String deviceId);
    void skip(Authorization authorization, String deviceId);
    void previous(Authorization authorization, String deviceId);
    void setVolume(Authorization authorization, int volume, String deviceId);
    void toggleShuffle(Authorization authorization, boolean state, String deviceId);
    ArrayList<Track> getQueue(Authorization authorization);

    Track getCurrentlyPlaying(Authorization authorization);

    void repeat(Authorization authorization, String deviceId);
}
