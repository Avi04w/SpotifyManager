package use_case.player;

import data_access.Authorization;

public class PlayerInputData {
    final private Authorization authorization;
    final private PlayerDataAccessInterface playerDB;

    public PlayerInputData(Authorization authorization, PlayerDataAccessInterface playerDB) {
        this.authorization = authorization;
        this.playerDB = playerDB;
    }

    /**
     * Retrieves the Authorization object associated with this PlayerManager.
     *
     * @return The Authorization object.
     */
    public Authorization getAuthorization() {
        return authorization;
    }

    /**
     * Pauses playback on the specified device.
     *
     * @param authorization The Authorization object containing the Spotify API access token.
     * @param deviceId       The ID of the device on which playback should be paused.
     */
    public void pause(Authorization authorization, String deviceId) {
        playerDB.pause(authorization, deviceId);
    }

    /**
     * Skips to the previous track on the specified device.
     *
     * @param authorization The Authorization object containing the Spotify API access token.
     * @param deviceId       The ID of the device on which to skip to the previous track.
     */
    public void previous(Authorization authorization, String deviceId) {
        playerDB.previous(authorization, deviceId);
    }

    /**
     * Resumes playback on the specified device.
     *
     * @param authorization The Authorization object containing the Spotify API access token.
     * @param deviceId       The ID of the device on which playback should be resumed.
     */
    public void resume(Authorization authorization, String deviceId) {
        playerDB.resume(authorization, deviceId);
    }

    /**
     * Sets the volume on the specified device.
     *
     * @param authorization The Authorization object containing the Spotify API access token.
     * @param volume         The volume level to set (0 to 100).
     * @param deviceId       The ID of the device on which to set the volume.
     */
    public void setVolume(Authorization authorization, int volume, String deviceId) {
        playerDB.setVolume(authorization, volume, deviceId);
    }

    /**
     * Skips to the next track on the specified device.
     *
     * @param authorization The Authorization object containing the Spotify API access token.
     * @param deviceId       The ID of the device on which to skip to the next track.
     */
    public void skip(Authorization authorization, String deviceId) {
        playerDB.skip(authorization, deviceId);
    }

    /**
     * Toggles the shuffle mode on the specified device.
     *
     * @param authorization The Authorization object containing the Spotify API access token.
     * @param state          The desired state of the shuffle mode (true for enabled, false for disabled).
     * @param deviceId       The ID of the device on which to toggle shuffle mode.
     */
    public void toggleShuffle(Authorization authorization, boolean state, String deviceId) {
        playerDB.toggleShuffle(authorization, state, deviceId);
    }

    /**
     * Sets the repeat mode on the specified device.
     *
     * @param authorization The Authorization object containing the Spotify API access token.
     * @param deviceId       The ID of the device on which to set the repeat mode.
     * @param repeat         The desired repeat mode ("track", "context", or "off").
     */
    public void repeat(Authorization authorization, String deviceId, String repeat) {
        playerDB.repeat(authorization, deviceId, repeat);
    }

    /**
     * Retrieves the queue of tracks associated with the provided Authorization.
     *
     * @param authorization The Authorization object containing the Spotify API access token.
     * @return An ArrayList of Track objects representing the track queue.
     */
    public void getQueue(Authorization authorization) {
        playerDB.getQueue(authorization);
    }

}
