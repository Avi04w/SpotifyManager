package data_access;

import entity.Album;
import entity.Artist;
import entity.Player;
import entity.Track;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.player.PlayerDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class PlayerDAO implements PlayerDataAccessInterface {
    /**
     * Retrieves information about the current playback status and details of the user's Spotify player.
     * The method sends a request to the Spotify API endpoint "https://api.spotify.com/v1/me/player" using the
     * provided authorization token. It processes the API response to construct and return a Player object
     * containing information such as the current track, playback status, volume, device, and more.
     *
     * @param authorization The Authorization object containing the necessary access token for authentication.
     * @return A Player object representing the current playback status and details of the user's Spotify player.
     * @throws JSONException If there is an issue parsing the JSON response from the Spotify API.
     * @throws RuntimeException If there is an issue with the Spotify API request/response or if the device is not online.
     */
    @Override
    public Player getPlayer(Authorization authorization) throws JSONException{

        String url = "https://api.spotify.com/v1/me/player";

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + authorization.getSpotifyApi().getAccessToken())
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                JSONObject trackJSON = responseBody.getJSONObject("item");

                JSONArray artistsJSON = trackJSON.getJSONArray("artists");
                ArrayList<Artist> artists = new ArrayList<>();

                for (int i = 0; i < artistsJSON.length(); i++){
                    JSONObject artistJSON = artistsJSON.getJSONObject(i);

                    String artistId = artistJSON.getString("id");
                    artists.add(new ArtistDAO().getArtist(authorization, artistId));
                }

                JSONObject albumJSON = trackJSON.getJSONObject("album");
                String albumId = albumJSON.getString("id");

                Album album = new AlbumDAO().getAlbum(authorization, albumId);

                Track track = Track.builder()
                        .album(album)
                        .artists(artists)
                        .duration_ms(trackJSON.getInt("duration_ms"))
                        .explicit(trackJSON.getBoolean("explicit"))
                        .id(trackJSON.getString("id"))
                        .name(trackJSON.getString("name"))
                        .uri(trackJSON.getString("uri"))
                        .build();

                String deviceId = responseBody.getJSONObject("device").getString("id");
                int volume = responseBody.getJSONObject("device").getInt("volume_percent");

                return Player.builder()
                        .isPlaying(responseBody.getBoolean("is_playing"))
                        .track(track)
                        .progress(responseBody.getInt("progress_ms"))
                        .volume(volume)
                        .shuffle(responseBody.getBoolean("shuffle_state"))
                        .device(deviceId)
                        .repeat(responseBody.getString("repeat_state"))
                        .build();
            } else {
                throw new RuntimeException(responseBody.getJSONObject("error").getString("message"));
            }

        } catch (IOException | RuntimeException e) {
            throw new RuntimeException("Device is not online!", e);
        }

    }

    /**
     * Retrieves the ID of the first available device for the user's Spotify playback.
     *This method sends a request to the Spotify API endpoint "https://api.spotify.com/v1/me/player/devices"
     * @param authorization The Authorization object containing the necessary access token for authentication.
     * @return The ID of the first available device.
     * @throws RuntimeException If there are no available devices or if there is an issue with the Spotify API request/response.
     */
    @Override
    public String getAvailableDevice(Authorization authorization) {
        String url = "https://api.spotify.com/v1/me/player/devices";

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + authorization.getSpotifyApi().getAccessToken())
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                return responseBody.getJSONArray("devices").getJSONObject(0).getString("id");
            } else {
                throw new RuntimeException(responseBody.getJSONObject("error").getString("message"));
            }

        } catch (IOException | JSONException e) {
            throw new RuntimeException("No available devices!", e);
        }
    }

    /**
     * Resumes playback on the specified Spotify playback device.
     * This method sends a PUT request to the Spotify API endpoint "https://api.spotify.com/v1/me/player/play"
     * to resume playback on the user's Spotify player. The request includes the provided authorization token
     * and the ID of the target playback device.
     *
     * @param authorization The Authorization object containing the necessary access token for authentication.
     * @param deviceId       The ID of the target Spotify playback device.
     * @throws RuntimeException If there is an issue with the Spotify API request/response or playback cannot be resumed.
     */
    @Override
    public void resume(Authorization authorization, String deviceId) {
        String baseUrl = "https://api.spotify.com/v1/me/player/play";
        makePutCall(authorization, deviceId, baseUrl);
    }

    /**
     * Pauses playback on the specified Spotify playback device.
     * This method sends a PUT request to the Spotify API endpoint "https://api.spotify.com/v1/me/player/pause"
     * to pause playback on the user's Spotify player. The request includes the provided authorization token
     * and the ID of the target playback device.
     *
     * @param authorization The Authorization object containing the necessary access token for authentication.
     * @param deviceId       The ID of the target Spotify playback device.
     * @throws RuntimeException If there is an issue with the Spotify API request/response or playback cannot be paused.
     */
    @Override
    public void pause(Authorization authorization, String deviceId) {
        String baseUrl = "https://api.spotify.com/v1/me/player/pause";
        makePutCall(authorization, deviceId, baseUrl);
    }

    /**
     * Skips to the next track on the specified Spotify playback device.
     * This method sends a POST request to the Spotify API endpoint "https://api.spotify.com/v1/me/player/next"
     * to skip to the next track on the user's Spotify player. The request includes the provided authorization token
     * and the ID of the target playback device.
     *
     * @param authorization The Authorization object containing the necessary access token for authentication.
     * @param deviceId       The ID of the target Spotify playback device.
     * @throws RuntimeException If there is an issue with the Spotify API request/response or skipping to the next track fails.
     */
    @Override
    public void skip(Authorization authorization, String deviceId) {
        String baseUrl = "https://api.spotify.com/v1/me/player/next";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
        urlBuilder.addQueryParameter("device_id", deviceId);

        makePostCall(authorization, urlBuilder);
    }

    /**
     * Returns to the previous track on the specified Spotify playback device.
     * This method sends a POST request to the Spotify API endpoint "https://api.spotify.com/v1/me/player/previous"
     * to return to the previous track on the user's Spotify player. The request includes the provided authorization token
     * and the ID of the target playback device.
     *
     * @param authorization The Authorization object containing the necessary access token for authentication.
     * @param deviceId       The ID of the target Spotify playback device.
     * @throws RuntimeException If there is an issue with the Spotify API request/response or returning to the previous track fails.
     */
    @Override
    public void previous(Authorization authorization, String deviceId) {
        String baseUrl = "https://api.spotify.com/v1/me/player/previous";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
        urlBuilder.addQueryParameter("device_id", deviceId);

        makePostCall(authorization, urlBuilder);
    }

    /**
     * Sets the volume on the specified Spotify playback device.
     * This method sends a PUT request to the Spotify API endpoint "https://api.spotify.com/v1/me/player/volume"
     * to set the volume on the user's Spotify player. The request includes the provided authorization token,
     * the ID of the target playback device, and the desired volume level.
     *
     * @param authorization The Authorization object containing the necessary access token for authentication.
     * @param volume         The desired volume level (0 to 100).
     * @param deviceId       The ID of the target Spotify playback device.
     * @throws RuntimeException If there is an issue with the Spotify API request/response or setting the volume fails.
     */
    @Override
    public void setVolume(Authorization authorization, int volume, String deviceId) {
        String baseUrl = "https://api.spotify.com/v1/me/player/volume";

        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
        urlBuilder.addQueryParameter("volume_percent", String.valueOf(volume));

        makePutCall(authorization, deviceId, urlBuilder.toString());
    }

    /**
     * Toggles shuffle mode on or off on the specified Spotify playback device.
     * This method sends a PUT request to the Spotify API endpoint "https://api.spotify.com/v1/me/player/shuffle"
     * to toggle shuffle mode on or off on the user's Spotify player. The request includes the provided authorization token,
     * the ID of the target playback device, and the desired shuffle state.
     *
     * @param authorization The Authorization object containing the necessary access token for authentication.
     * @param state          The desired shuffle state (true for on, false for off).
     * @param deviceId       The ID of the target Spotify playback device.
     * @throws RuntimeException If there is an issue with the Spotify API request/response or toggling shuffle mode fails.
     */
    @Override
    public void toggleShuffle(Authorization authorization, boolean state, String deviceId) {
        String baseUrl = "https://api.spotify.com/v1/me/player/shuffle";

        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
        urlBuilder.addQueryParameter("state", String.valueOf(state));

        makePutCall(authorization, deviceId, urlBuilder.toString());
    }

    /**
     * Retrieves the queue of tracks in the user's Spotify playback.
     * This method sends a request to the Spotify API endpoint "https://api.spotify.com/v1/me/player/queue"
     * using the provided authorization token. It extracts the track information from the API response and returns
     * an ArrayList of Track objects representing the current queue.
     *
     * @param authorization The Authorization object containing the necessary access token for authentication.
     * @return An ArrayList of Track objects representing the current queue in the user's Spotify playback.
     * @throws RuntimeException If there is an issue with the Spotify API request/response or retrieving the queue fails.
     */
    @Override
    public ArrayList<Track> getQueue(Authorization authorization) {
        String url = "https://api.spotify.com/v1/me/player/queue";

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + authorization.getSpotifyApi().getAccessToken())
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                JSONArray queueJSON = responseBody.getJSONArray("queue");

                ArrayList<Track> tracks = new ArrayList<>();
                for (int i = 0; i < queueJSON.length(); i++){
                    String trackId = queueJSON.getJSONObject(i).getString("id");
                    tracks.add(new TrackDAO().getTrack(authorization, trackId));
                }

                return tracks;

            } else {
                throw new RuntimeException(responseBody.getJSONObject("error").getString("message"));
            }

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves information about the currently playing track on the user's Spotify playback.
     * This method sends a request to the Spotify API endpoint "https://api.spotify.com/v1/me/player/currently_playing"
     * using the provided authorization token. It extracts the track information from the API response and returns
     * a Track object representing the currently playing track.
     *
     * @param authorization The Authorization object containing the necessary access token for authentication.
     * @return A Track object representing the currently playing track on the user's Spotify playback.
     * @throws RuntimeException If there is an issue with the Spotify API request/response or retrieving the currently playing track fails.
     */
    public Track getCurrentlyPlaying(Authorization authorization) {
        String url = "https://api.spotify.com/v1/me/player/currently_playing";

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + authorization.getSpotifyApi().getAccessToken())
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                JSONObject trackJSON = responseBody.getJSONObject("item");

                JSONArray artistsJSON = trackJSON.getJSONArray("artists");
                ArrayList<Artist> artists = new ArrayList<>();

                for (int i = 0; i < artistsJSON.length(); i++){
                    JSONObject artistJSON = artistsJSON.getJSONObject(i);

                    String artistId = artistJSON.getString("id");
                    artists.add(new ArtistDAO().getArtist(authorization, artistId));
                }

                JSONObject albumJSON = trackJSON.getJSONObject("album");
                String albumId = albumJSON.getString("id");

                Album album = new AlbumDAO().getAlbum(authorization, albumId);

                Track track = Track.builder()
                        .album(album)
                        .artists(artists)
                        .duration_ms(trackJSON.getInt("duration_ms"))
                        .explicit(trackJSON.getBoolean("explicit"))
                        .id(trackJSON.getString("id"))
                        .name(trackJSON.getString("name"))
                        .uri(trackJSON.getString("uri"))
                        .build();

                return track;

            } else {
                throw new RuntimeException(responseBody.getJSONObject("error").getString("message"));
            }

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the repeat mode on the specified Spotify playback device.
     * This method sends a PUT request to the Spotify API endpoint "https://api.spotify.com/v1/me/player/repeat"
     * to set the repeat mode on the user's Spotify player. The request includes the provided authorization token,
     * the ID of the target playback device, and the desired repeat mode.
     *
     * @param authorization The Authorization object containing the necessary access token for authentication.
     * @param deviceId       The ID of the target Spotify playback device.
     * @param repeat         The desired repeat mode ("track", "context", or "off").
     * @throws RuntimeException If there is an issue with the Spotify API request/response or setting the repeat mode fails.
     */
    public void repeat(Authorization authorization, String deviceId, String repeat) {
        String baseUrl = "https://api.spotify.com/v1/me/player/repeat?state=" + repeat;
        makePutCall(authorization, deviceId, baseUrl);
    }

    /**
     * Makes a POST request to the specified URL using the provided Authorization and URL builder.
     *
     * @param authorization The Authorization object containing the necessary access token for authentication.
     * @param urlBuilder     The HTTP URL builder for constructing the complete URL.
     * @throws RuntimeException If there is an issue with the HTTP request or the Spotify API response.
     */
    private void makePostCall(Authorization authorization, HttpUrl.Builder urlBuilder) {
        String url = urlBuilder.build().toString();

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        RequestBody formBody = new FormBody.Builder()
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .addHeader("Authorization", "Bearer " + authorization.getSpotifyApi().getAccessToken())
                .build();

        try {
            Response response = client.newCall(request).execute();

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Makes a PUT request to the specified base URL with additional parameters using the provided Authorization and device ID.
     *
     * @param authorization The Authorization object containing the necessary access token for authentication.
     * @param deviceId       The ID of the target Spotify device.
     * @param baseUrl        The base URL for the PUT request.
     * @throws RuntimeException If there is an issue with the HTTP request or the Spotify API response.
     */
    private void makePutCall(Authorization authorization, String deviceId, String baseUrl) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
        urlBuilder.addQueryParameter("device_id", deviceId);

        String url = urlBuilder.build().toString();

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        RequestBody formBody = new FormBody.Builder()
                .build();

        Request request = new Request.Builder()
                .url(url)
                .put(formBody)
                .addHeader("Authorization", "Bearer " + authorization.getSpotifyApi().getAccessToken())
                .build();

        try {
            Response response = client.newCall(request).execute();

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
