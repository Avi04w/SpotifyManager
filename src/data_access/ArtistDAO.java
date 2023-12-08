package data_access;

import entity.Artist;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.artist.ArtistDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;

public class ArtistDAO implements ArtistDataAccessInterface {
    /**
     * Retrieves information about a Spotify artist using the provided artist ID and authorization token.
     * The method sends a GET request to the Spotify API's "artists" endpoint and parses the response
     * to construct an Artist object representing the requested artist.
     *
     * @param authorization The Authorization object containing the Spotify API access token.
     * @param id            The unique identifier (ID) of the Spotify artist.
     * @return An Artist object containing information about the requested artist.
     * @throws JSONException If there is an issue parsing the JSON response from the Spotify API.
     * @throws RuntimeException If there is an issue with the HTTP request or if the API returns an error.
     */
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

    static ArrayList<Artist> getArtistsArray(Authorization authorization, JSONArray artistsJSON) {
        ArrayList<Artist> artists = new ArrayList<>();
        for (int i = 0; i < artistsJSON.length(); i++){
            JSONObject artistJSON = artistsJSON.getJSONObject(i);
            String artistId = artistJSON.getString("id");
            artists.add(new ArtistDAO().getArtist(authorization, artistId));
        }

        return artists;
    }
}
