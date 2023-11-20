package api;

import entity.Album;
import entity.Artist;
import entity.Track;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MongoTrackDB implements TrackDB{
    @Override
    public Track getTrack(Authorization authorization, String id) throws JSONException {

        String url = "https://api.spotify.com/v1/tracks/";

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url + id)
                .addHeader("Authorization", "Bearer " + authorization.getSpotifyApi().getAccessToken())
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                JSONArray artistsJSON = responseBody.getJSONArray("artists");
                ArrayList<Artist> artists = MongoArtistDB.getArtistsArray(authorization, artistsJSON);

                return Track.builder()
                        .album(new MongoAlbumDB().getAlbum(authorization, responseBody.getJSONObject("album").getString("id")))
                        .artists(artists)
                        .duration_ms(responseBody.getInt("duration_ms"))
                        .explicit(responseBody.getBoolean("explicit"))
                        .id(responseBody.getString("id"))
                        .name(responseBody.getString("name"))
                        .uri(responseBody.getString("uri"))
                        .build();
            } else {
                throw new RuntimeException(responseBody.getJSONObject("error").getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
