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

public class PlayerDAO implements PlayerDataAccessInterface {
    @Override
    public Player getPlayer(Authorization authorization) {

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

                return Player.builder()
                        .isPlaying(responseBody.getBoolean("is_playing"))
                        .track(track)
                        .progress(responseBody.getInt("progress_ms"))
                        .volume(responseBody.getJSONObject("device").getInt("volume_percent"))
                        .shuffle(responseBody.getBoolean("shuffle_state"))
                        .build();
            } else {
                throw new RuntimeException(responseBody.getJSONObject("error").getString("message"));
            }

        } catch (IOException | RuntimeException e) {
            throw new RuntimeException("Device is not online!", e);
        }

    }

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

    @Override
    public void resume(Authorization authorization, String deviceId) {
        String baseUrl = "https://api.spotify.com/v1/me/player/play";
        makePutCall(authorization, deviceId, baseUrl);
    }

    @Override
    public void pause(Authorization authorization, String deviceId) {
        String baseUrl = "https://api.spotify.com/v1/me/player/pause";
        makePutCall(authorization, deviceId, baseUrl);
    }

    @Override
    public void skip(Authorization authorization, String deviceId) {
        String baseUrl = "https://api.spotify.com/v1/me/player/next";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
        urlBuilder.addQueryParameter("device_id", deviceId);

        makePostCall(authorization, urlBuilder);
    }

    @Override
    public void previous(Authorization authorization, String deviceId) {
        String baseUrl = "https://api.spotify.com/v1/me/player/previous";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
        urlBuilder.addQueryParameter("device_id", deviceId);

        makePostCall(authorization, urlBuilder);
    }

    @Override
    public void setVolume(Authorization authorization, int volume, String deviceId) {
        String baseUrl = "https://api.spotify.com/v1/me/player/volume";

        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
        urlBuilder.addQueryParameter("volume_percent", String.valueOf(volume));

        makePutCall(authorization, deviceId, urlBuilder.toString());
    }

    @Override
    public void toggleShuffle(Authorization authorization, boolean state, String deviceId) {
        String baseUrl = "https://api.spotify.com/v1/me/player/shuffle";

        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
        urlBuilder.addQueryParameter("state", String.valueOf(state));

        makePutCall(authorization, deviceId, urlBuilder.toString());
    }

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
