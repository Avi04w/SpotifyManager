package api;

import entity.Album;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MongoAlbumDB {
    @Override
    public Album getAlbum(Authorization authorization, String id) throws JSONException {

        String url = "https://api.spotify.com/v1/albums/";

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url + id)
                .addHeader("Authorization", "Bearer " + authorization.getSpotifyApi().getAccessToken())
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                return Album.builder()
                        .name(responseBody.getString("name"))
                        .id(responseBody.getString("id"))
                        .uri(responseBody.getString("uri"))
//                        .artists(responseBody.getString("artists"))
                        .type(responseBody.getString("album_type"))
//                        .image(responseBody.getString("image"))
                        .totalTracks(responseBody.getInt("total_tracks"))
//                        .tracks(responseBody.getString("tracks"))
//                        .genres(responseBody.getString("genres"))
                        .popularity(responseBody.getInt("popularity"))
                        .build();
            } else {
                throw new RuntimeException(responseBody.getJSONObject("error").getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
}


