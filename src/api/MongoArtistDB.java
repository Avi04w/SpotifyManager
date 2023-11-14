package api;

import entity.Artist;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MongoArtistDB implements ArtistDB {
    @Override
    public Artist getArtist(Authorization authorization, String id) throws JSONException {

        String url = "https://api.spotify.com/v1/artists/";

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url + id)
                .addHeader("Authorization", "Bearer " + authorization.getSpotifyApi().getAccessToken())
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                return Artist.builder()
                        .name(responseBody.getString("name"))
                        .id(responseBody.getString("id"))
//                      .genres(artist.getJSONArray("id"))
//                        .popularity(responseBody.getInt("popularity"))
                        .image(responseBody.getJSONArray("images").getJSONObject(0).getString("url"))
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
