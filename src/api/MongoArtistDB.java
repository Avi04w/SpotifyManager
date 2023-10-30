package api;

import entity.Artist;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MongoArtistDB implements ArtistDB {
    private static final String API_TOKEN = System.getenv("API_TOKEN");

    public static String getApiToken() {
        return API_TOKEN;
    }

    @Override
    public Artist getArtist() throws JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg")
                .addHeader("Authorization", "Bearer  " + API_TOKEN)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());
             return Artist.builder()
                     .name(responseBody.getString("name"))
                     .id(responseBody.getString("id"))
//                      .genres(artist.getJSONArray("id"))
                     .popularity(responseBody.getInt("popularity"))
                     .uri(responseBody.getString("uri"))
                     .build();
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
