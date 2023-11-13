package api;

import entity.Album;
import entity.Artist;
import entity.Player;
import entity.Track;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MongoPlayerDB implements PlayerDB{
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
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody);

            if (response.code() == 200) {
                JSONObject trackResponse = responseBody.getJSONObject("item");

                ArrayList<Artist> artists = new ArrayList<Artist>();

                Track track = Track.builder()
                        .artists(artists)
                        .duration_ms(trackResponse.getInt("duration_ms"))
                        .explicit(trackResponse.getBoolean("explicit"))
                        .id(trackResponse.getString("id"))
                        .name(trackResponse.getString("name"))
                        .uri(trackResponse.getString("uri"))
                        .is_playable(trackResponse.getBoolean("is_playable"))
                        .build();

                return Player.builder()
                        .name(responseBody.getString("name"))
                        .isPlaying(responseBody.getBoolean("is_playing"))
                        .track(track)
                        .progress(responseBody.getInt("progress_ms"))
                        .build();
            } else {
                throw new RuntimeException(responseBody.getJSONObject("error").getString("message"));
            }

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
