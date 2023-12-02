package interface_adapter;

import data_access.Authorization;

public class PlayerState {
    private Authorization authorization;
    private String deviceId;
    private boolean isPlaying;
    private int progress;
    private int volume;
    private boolean shuffle;
    public PlayerState(PlayerState copy){
        authorization = copy.authorization;
        deviceId = copy.deviceId;
        isPlaying = copy.isPlaying;
        progress = copy.progress;
        volume = copy.volume;
        shuffle = copy.shuffle;
    }
    public PlayerState() {}

    public Authorization getAuthorization() {return authorization;}

    public String getDeviceId() {
        return deviceId;
    }
    public boolean getIsPlaying() {
        return isPlaying;
    }

    public int getProgress() {
        return progress;
    }

    public int getVolume() {
        return volume;
    }
    public boolean getShuffle() {
        return shuffle;
    }

    public void setAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setPlaying(boolean playing) {
        this.isPlaying = playing;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
    }
}
