package api;

import entity.Album;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoAlbumDB implements AlbumDB{
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

                String artistsString = responseBody.getString("artists");
                List<String> artistsList = new ArrayList<String>(Arrays.asList(artistsString.split(",")));

                String imageString = responseBody.getString("image");
                List<String> imageList = new ArrayList<String>(Arrays.asList(imageString.split(",")));

                String tracksString = responseBody.getString("tracks");
                List<String> tracksList = new ArrayList<String>(Arrays.asList(tracksString.split(",")));

                String genresString = responseBody.getString("genres");
                List<String> genresList = new ArrayList<String>(Arrays.asList(genresString.split(",")));

                return Album.builder()
                        .name(responseBody.getString("name"))
                        .id(responseBody.getString("id"))
                        .uri(responseBody.getString("uri"))
                        .artists(artistsList)
                        .type(responseBody.getString("album_type"))
                        .image((ArrayList<String>) imageList)
                        .totalTracks(responseBody.getInt("total_tracks"))
                        .tracks(tracksList)
                        .genres((ArrayList<String>) genresList)
                        .popularity(responseBody.getInt("popularity"))
                        .build();
            } else {
                throw new RuntimeException(responseBody.getJSONObject("error").getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }

    }
}


