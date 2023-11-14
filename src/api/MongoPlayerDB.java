package api;

import entity.Album;
import entity.Artist;
import entity.Player;
import entity.Track;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.GetArtistUseCase;

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
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                JSONObject trackJSON = responseBody.getJSONObject("item");

                JSONArray artistsJSON = trackJSON.getJSONArray("artists");
                ArrayList<Artist> artists = new ArrayList<>();

                for (int i = 0; i < artistsJSON.length(); i++){
                    JSONObject artistJSON = artistsJSON.getJSONObject(i);

                    String artistId = artistJSON.getString("id");
                    artists.add(new MongoArtistDB().getArtist(authorization, artistId));
                }

                JSONObject albumJSON = trackJSON.getJSONObject("album");
                String albumId = albumJSON.getString("id");

                Album album = new MongoAlbumDB().getAlbum(authorization, albumId);

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
                        .build();
            } else {
                throw new RuntimeException(responseBody.getJSONObject("error").getString("message"));
            }

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
